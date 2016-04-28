angular.module('monolitApp.admin.consumer', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.consumer', {
                url: "/consumer",
                views: {
                    'admin': {
                        templateUrl: "admin/consumer/consumer.html",
                        controller: 'adminConsumerCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление проиизводителями'
                }
            })
    })

    .factory('getAllConsumersFactory', function ($resource) {
        return $resource('/consumer/all', {}, {'query': {method: 'GET', isArray: true}});
    })

    .factory('saveConsumerFactory', function ($resource) {
        return $resource('/product/save', {}, {'save': {method: 'POST'}});
    })

    .controller('adminConsumerCtrl', function ($rootScope, $scope, getAllConsumersFactory) {
        
        $scope.consumerList = getAllConsumersFactory.query();

    });
