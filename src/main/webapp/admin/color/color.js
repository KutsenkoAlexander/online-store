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

    .factory('saveColorFactory', function ($resource) {
        return $resource('/admin/color/save', {}, {'save': {method: 'POST'}});
    })

    .factory('deleteColorFactory', function ($resource) {
        return $resource('/admin/color/delete/:id', {id: '@id'},{'delete': { method: 'DELETE' }});
    })

    .controller('adminColorCtrl', function ($rootScope,
                                            $scope,
                                            getAllColorsFactory,
                                            saveColorFactory,
                                            deleteColorFactory) {

        $scope.fastEditColor = false;

        var editableName;

        $scope.colorList = getAllColorsFactory.query();

        $scope.selectColorFromList = function(adminColorSelect){
            $rootScope.$broadcast('selectColor', {
                selectColor: adminColorSelect
            });
        };

        $scope.$on("colorBroadcastToList", function (event, args) {
            $scope.adminColorSelect = args.colorToList;
        });

        $scope.editColor = function(id, name){
            $rootScope.$broadcast('cancelEditColorBroadcast', {
                editBroadcast: name
            });
            $scope.fastEditColor = true;
            $scope.editColorItem = id;
            $scope.colorName = name;

            $scope.$on("saveEditColorBroadcast", function (event, args) {
                editableName = args.saveColorBroadcast;
            });
        };

        $scope.saveNewColor = function(name){
            if( name === null ||
                name === '' ||
                name === 0 ||
                name === NaN ||
                angular.isUndefined(name)){
                alert("Введите корректные данные!");
            } else {
                var color = {
                    "idSprColors": null,
                    "name": name
                };
                saveColorFactory.save(color,
                    function (resp, headers) {
                        //success callback
                        $scope.colorList = getAllColorsFactory.query();
                        $scope.nameColor = null;
                        $scope.fastEditColor = false;
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    });
            }
        };

        $scope.saveEditableColor = function(id){
            if( editableName === null ||
                editableName === '' ||
                editableName === 0 ||
                editableName === NaN ||
                angular.isUndefined(editableName)){
                alert("Введите корректные данные!");
            } else {
                var color = {
                    "idSprColors": id,
                    "name": editableName
                };
                saveColorFactory.save(color,
                    function (resp, headers) {
                        //success callback
                        $scope.colorList = getAllColorsFactory.query();
                        $scope.nameColor = null;
                        $scope.fastEditColor = false;
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    });
            }
        };

        $scope.cancelColor = function(colorName){
            $scope.fastEditColor = false;
            $scope.nameColor = null;
            $rootScope.$broadcast('cancelEditColorBroadcast',{
                editBroadcast: colorName
            })
        };

        $scope.deleteColor = function(id, name){
            var result = confirm('Удалить \"'+name+'\" ?');
            if(result){
                deleteColorFactory.delete({id:id},
                    function (resp, headers) {
                        //success callback
                        $scope.colorList = getAllColorsFactory.query();
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    }
                );
            }
        };

    })

    .controller('secondColorCtrl', function($rootScope, $scope){
        $scope.$on('cancelEditColorBroadcast', function (event, args) {
            $scope.colorName = args.editBroadcast;
        });

        $scope.$watch('colorName', function () {
            $rootScope.$broadcast('saveEditColorBroadcast',{
                saveColorBroadcast: $scope.colorName
            });
        });

    });
