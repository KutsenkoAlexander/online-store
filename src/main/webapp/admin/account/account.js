angular.module('monolitApp.login', ['ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('login', {
                url: "/login",
                views: {
                    'content': {
                        templateUrl: "admin/account/login.html"
                        //controller: 'adminCategoryCtrl'
                    }
                },
                data: {
                    pageTitle: 'Авторизация'
                }
            })
    });