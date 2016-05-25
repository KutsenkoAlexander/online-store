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

    .controller('accountCtrl', function($rootScope, $http, $scope, $state, $location) {
        var self = this;

        if (localStorage.getItem("session")) {
            $scope.authenticated = true;
            self.error = false;
            if ($location.path() === "/login") {
                $location.path("/admin");
            } else {
                $location.path($location.path());
            }
        } else {
            $location.path("/login");
            self.error = true;
        }

        $scope.login = function () {
            var credentials = {
                "username": $scope.username,
                "password": $scope.password
            };
            return $http.post("/login", "username=" + $scope.username +
                "&password=" + $scope.password, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            } ).then(function(data) {
                var encoderAccount = btoa(credentials.username + ":" + credentials.password);
                localStorage.setItem("session", encoderAccount);
                $location.path("/admin");
            }, function(err) {
                alert(err.statusText);
            });
        };

        $scope.logout = function(){
            localStorage.removeItem("session");
            $location.path("/");
        }

    });