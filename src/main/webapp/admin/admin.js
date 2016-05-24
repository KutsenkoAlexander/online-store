angular.module('monolitApp.admin', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
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
    })

    .controller('adminCtrl', function($rootScope, $scope, $state, $http){});
