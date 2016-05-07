angular.module('monolitApp.admin.size', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.size', {
                url: "/size",
                views: {
                    'admin': {
                        templateUrl: "admin/size/size.html",
                        controller: 'adminSizeCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление размерами'
                }
            })
    })

    .factory('getAllSizesFactory', function ($resource) {
        return $resource('/size/all', {}, {'query': {method: 'GET', isArray: true}});
    })

    .factory('saveSizesFactory', function ($resource) {
        return $resource('/size/save', {}, {'save': {method: 'POST'}});
    })

    .controller('adminSizeCtrl', function ($rootScope, $scope, getAllSizesFactory) {

        $scope.sizeList = getAllSizesFactory.query();

        $scope.selectSizeFromList = function(sizeSelect){
            $rootScope.$broadcast('selectSize', {
                selectSize: sizeSelect
            });
        };

        $scope.$on("sizeBroadcastToList", function (event, args) {
            $scope.sizeSelect = args.sizeToList;
        });

    });
