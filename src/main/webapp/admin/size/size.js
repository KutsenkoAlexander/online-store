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

    .factory('saveSizeFactory', function ($resource) {
        return $resource('/rest/size/save', {}, {'save': {method: 'POST'}});
    })

    .factory('deleteSizeFactory', function ($resource) {
        return $resource('/rest/size/delete/:id', {id: '@id'},{'delete': { method: 'DELETE' }});
    })

    .controller('adminSizeCtrl', function ($rootScope,
                                           $scope,
                                           getAllSizesFactory,
                                           saveSizeFactory,
                                           deleteSizeFactory) {
        $scope.fastEditSize = false;

        var editableName;

        $scope.sizeList = getAllSizesFactory.query();

        $scope.selectSizeFromList = function(sizeSelect){
            $rootScope.$broadcast('selectSize', {
                selectSize: sizeSelect
            });
        };

        $scope.$on("sizeBroadcastToList", function (event, args) {
            $scope.sizeSelect = args.sizeToList;
        });

        $scope.editSize = function(id, name){
            $rootScope.$broadcast('cancelEditSizeBroadcast', {
                editBroadcast: name
            });
            $scope.fastEditSize = true;
            $scope.editSizeItem = id;
            $scope.sizeName = name;

            $scope.$on("saveEditSizeBroadcast", function (event, args) {
                editableName = args.saveSizeBroadcast;
            });
        };

        $scope.saveNewSize = function(name){
            if( name === null ||
                name === '' ||
                name === 0 ||
                name === NaN ||
                angular.isUndefined(name)){
                alert("Введите корректные данные!");
            } else {
                var size = {
                    "idSprSize": null,
                    "name": name
                };
                saveSizeFactory.save(size,
                    function (resp, headers) {
                        //success callback
                        $scope.sizeList = getAllSizesFactory.query();
                        $scope.nameSize = null;
                        $scope.fastEditSize = false;
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    });
            }
        };

        $scope.saveEditableSize = function(id){
            if( editableName === null ||
                editableName === '' ||
                editableName === 0 ||
                editableName === NaN ||
                angular.isUndefined(editableName)){
                alert("Введите корректные данные!");
            } else {
                var size = {
                    "idSprSize": id,
                    "name": editableName
                };
                saveSizeFactory.save(size,
                    function (resp, headers) {
                        //success callback
                        $scope.sizeList = getAllSizesFactory.query();
                        $scope.nameSize = null;
                        $scope.fastEditSize = false;
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    });
            }
        };

        $scope.cancelSize = function(sizeName){
            $scope.fastEditSize = false;
            $scope.nameSize = null;
            $rootScope.$broadcast('cancelEditSizeBroadcast',{
                editBroadcast: sizeName
            })
        };

        $scope.deleteSize = function(id, name){
            var result = confirm('Удалить \"'+name+'\" ?');
            if(result){
                deleteSizeFactory.delete({id:id},
                    function (resp, headers) {
                        //success callback
                        $scope.sizeList = getAllSizesFactory.query();
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    }
                );
            }
        };

    })

    .controller('secondSizeCtrl', function($rootScope, $scope){
        $scope.$on('cancelEditSizeBroadcast', function (event, args) {
            $scope.sizeName = args.editBroadcast;
        });

        $scope.$watch('sizeName', function () {
            $rootScope.$broadcast('saveEditSizeBroadcast',{
                saveSizeBroadcast: $scope.sizeName
            });
        });

    });
