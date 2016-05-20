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
                authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
            } : {};
            console.log(headers);
            $http.get('user', {headers: headers}).then(function (response) {
                if (response.data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }, function () {
                $rootScope.authenticated = false;
                callback && callback();
            });

        };

        authenticate();
        self.credentials = {};
        $scope.login = function () {
            alert(1);
            authenticate(self.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    self.error = false;
                } else {
                    $location.path("/login");
                    self.error = true;
                }
            });
        };
    });