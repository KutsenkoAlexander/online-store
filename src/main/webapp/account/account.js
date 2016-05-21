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
                authorization: btoa(credentials.username + ":" + credentials.password)
            } : {};
            $http.post('/user', null, {headers: headers}).then(
                function (response) {
                    if (response) {
                        $rootScope.authenticated = true;
                        console.log(response);
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }, function (err) {
                    $rootScope.authenticated = false;
                    callback && callback();
                    alert(err.statusText);
                }
            );
        };

        self.credentials = {};
        $scope.login = function () {
            self.credentials = {
                "username": $scope.username,
                "password": $scope.password
            };
            authenticate(self.credentials, function () {
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