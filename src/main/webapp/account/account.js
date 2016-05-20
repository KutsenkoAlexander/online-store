angular.module('monolitApp.login', ['ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                url: "/login",
                views: {
                    'content': {
                        templateUrl: "account/login.html",
                        controller: 'accountCtrl'
                    }
                },
                data: {
                    pageTitle: 'Авторизация'
                }
            })
    })

    .controller('accountCtrl', function($rootScope, $http, $scope, $location) {
        var self = this;

        var authenticate = function (credentials, callback) {
            var headers = credentials ? {
                Authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
            } : {};
            $http.get('/user', {headers: headers}).then(
                function (response) {
                    console.log(response);
                    if (response.data.name) {
                        $rootScope.authenticated = true;
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }, function () {
                    console.log(123);
                    $rootScope.authenticated = false;
                    callback && callback();
                }
            );
        };

        authenticate();
        self.credentials = {};
        $scope.login = function () {
            self.credentials = {
                "username": $scope.username,
                "password": $scope.password
            };
            authenticate(self.credentials, function () {
                console.log(self.credentials);
                if ($rootScope.authenticated) {
                    $location.path("/admin");
                    self.error = false;
                } else {
                    $location.path("/login");
                    self.error = true;
                }
            });
        };
    });