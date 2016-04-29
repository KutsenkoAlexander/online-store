angular.module('monolitApp.admin.type', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.type', {
                url: "/type",
                views: {
                    'admin': {
                        templateUrl: "admin/type/type.html",
                        controller: 'adminTypeCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление типами'
                }
            })
    })

    .factory('getAllTypesFactory', function ($resource) {
        return $resource('/type/all', {}, {'query': {method: 'GET', isArray: true}});
    })

    .factory('saveTypesFactory', function ($resource) {
        return $resource('/type/save', {}, {'save': {method: 'POST'}});
    })

    .controller('adminTypeCtrl', function ($rootScope, $scope, getAllTypesFactory) {

        $scope.typeList = getAllTypesFactory.query();

        $scope.selectType = function(adminTypeSelect){
            $rootScope.$broadcast('selectType', {
                selectType: adminTypeSelect
            });
        }

    });
