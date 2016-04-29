angular.module('monolitApp.admin', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin', {
                url: '/admin',
                views: {
                    'content': {
                        templateUrl: 'admin/admin.html'
                    }
                },
                data: {
                    pageTitle: 'Управление магазином'
                }
            })
    });
    