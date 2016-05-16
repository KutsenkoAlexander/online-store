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

    .factory('saveTypeFactory', function ($resource) {
        return $resource('/admin/type/save', {}, {'save': {method: 'POST'}});
    })

    .factory('deleteTypeFactory', function ($resource) {
        return $resource('/admin/type/delete/:id', {id: '@id'},{'delete': { method: 'DELETE' }});
    })

    .controller('adminTypeCtrl', function ($rootScope,
                                           $scope,
                                           getAllTypesFactory,
                                           saveTypeFactory,
                                           deleteTypeFactory) {
        $scope.fastEditType = false;

        var editableName;

        $scope.typeList = getAllTypesFactory.query();

        $scope.selectTypeFromList = function(adminTypeSelect){
            $rootScope.$broadcast('selectType', {
                selectType: adminTypeSelect
            });
        };

        $scope.$on("typeBroadcastToList", function (event, args) {
            $scope.adminTypeSelect = args.typeToList;
        });

        $scope.editType = function(id, name){
            $rootScope.$broadcast('cancelEditTypeBroadcast', {
                editBroadcast: name
            });
            $scope.fastEditType = true;
            $scope.editTypeItem = id;
            $scope.typeName = name;

            $scope.$on("saveEditTypeBroadcast", function (event, args) {
                editableName = args.saveTypeBroadcast;
            });
        };

        $scope.saveNewType = function(name){
            console.log(name);
            if( name === null ||
                name === '' ||
                name === 0 ||
                name === NaN ||
                angular.isUndefined(name)){
                alert("Введите корректные данные!");
            } else {
                var type = {
                    "idSprType": null,
                    "name": name
                };
                saveTypeFactory.save(type,
                    function (resp, headers) {
                        //success callback
                        $scope.typeList = getAllTypesFactory.query();
                        $scope.nameType = null;
                        $scope.fastEditType = false;
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    });
            }
        };

        $scope.saveEditableType = function(id){
            if( editableName === null ||
                editableName === '' ||
                editableName === 0 ||
                editableName === NaN ||
                angular.isUndefined(editableName)){
                alert("Введите корректные данные!");
            } else {
                var type = {
                    "idSprType": id,
                    "name": editableName
                };
                saveTypeFactory.save(type,
                    function (resp, headers) {
                        //success callback
                        $scope.typeList = getAllTypesFactory.query();
                        $scope.nameType = null;
                        $scope.fastEditType = false;
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    });
            }
        };

        $scope.cancelType = function(typeName){
            $scope.fastEditType = false;
            $scope.nameType = null;
            $rootScope.$broadcast('cancelEditTypeBroadcast',{
                editBroadcast: typeName
            })
        };

        $scope.deleteType = function(id, name){
            var result = confirm('Удалить \"'+name+'\" ?');
            if(result){
                deleteTypeFactory.delete({id:id},
                    function (resp, headers) {
                        //success callback
                        $scope.typeList = getAllTypesFactory.query();
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    }
                );
            }
        };

    })

    .controller('secondTypeCtrl', function($rootScope, $scope){
        $scope.$on('cancelEditTypeBroadcast', function (event, args) {
            $scope.typeName = args.editBroadcast;
        });

        $scope.$watch('typeName', function () {
            $rootScope.$broadcast('saveEditTypeBroadcast',{
                saveTypeBroadcast: $scope.typeName
            });
        });

    });
