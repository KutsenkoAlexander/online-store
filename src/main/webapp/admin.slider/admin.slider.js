angular.module('monolitApp.admin.slider', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.slider', {
                url: "/slider",
                views: {
                    'admin': {
                        templateUrl: "admin.slider/admin.slider.html"
                        // controller: 'adminSliderCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление слайдером'
                }
            });
    });