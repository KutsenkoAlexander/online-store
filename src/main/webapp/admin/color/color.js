angular.module('monolitApp.admin.color', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.color', {
                url: "/color",
                views: {
                    'admin': {
                        templateUrl: "admin/color/color.html",
                        controller: 'adminColorCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление цветами'
                }
            })
    })

    .factory('getAllColorsFactory', function ($resource) {
        return $resource('/color/all', {}, {'query': {method: 'GET', isArray: true}});
    })

    .factory('saveColorsFactory', function ($resource) {
        return $resource('/color/save', {}, {'save': {method: 'POST'}});
    })

    .controller('adminColorCtrl', function ($rootScope, $scope, getAllColorsFactory) {

        $scope.colorList = getAllColorsFactory.query();

        $scope.selectColorFromList = function(adminColorSelect){
            $rootScope.$broadcast('selectColor', {
                selectColor: adminColorSelect
            });
        };

        $scope.$on("colorBroadcastToList", function (event, args) {
            $scope.adminColorSelect = args.colorToList;
        });
        
    });
