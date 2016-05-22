angular.module('monolitApp.login', ['ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                url: "/login",
                views: {
                    'content': {
                        templateUrl: "account/login.html"
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
            var encoderAccount = btoa(credentials.username + ":" + credentials.password);
            var headers = credentials ? {
                authorization: "Basic "+encoderAccount
            } : {};
            $http.get('/user', {headers: headers}).then(
                function (response) {
                    if (response) {
                        $rootScope.authenticated = true;
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }, function(err) {
                    alert(err);
                    $rootScope.authenticated = false;
                    callback && callback();
                }
            );
        };

        self.credentials = {};
        $scope.login = function () {
            self.credentials = {
                "username": $scope.username,
                "password": $scope.password
            };
            authenticate(self.credentials, function() {
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