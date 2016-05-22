angular.module('monolitApp.admin', ['ui.router', 'ngResource'])

    .config(function ($stateProvider, $httpProvider) {
        $stateProvider
            .state('admin', {
                url: '/admin',
                views: {
                    'content': {
                        templateUrl: 'admin/admin.html',
                        controller: 'adminCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление магазином'
                }
            });
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    })

    .controller('adminCtrl', function($rootScope, $scope, $state){});
