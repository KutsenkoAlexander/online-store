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
        return $resource('/admin/consumer/save', {}, {'save': {method: 'POST'}});
    })

    .factory('deleteConsumerFactory', function ($resource) {
        return $resource('/admin/consumer/delete/:id', {id: '@id'},{'delete': { method: 'DELETE' }});
    })

    .controller('adminConsumerCtrl', function ($rootScope,
                                               $scope,
                                               getAllConsumersFactory,
                                               saveConsumerFactory,
                                               deleteConsumerFactory) {
        $scope.fastEditConsumer = false;

        var editableName;

        $scope.consumerList = getAllConsumersFactory.query();

        $scope.selectConsumerFromList = function(adminConsumerCtrl){
            $rootScope.$broadcast('selectConsumer', {
                selectConsumer: adminConsumerCtrl
            });
        };

        $scope.$on("consumerBroadcastToList", function (event, args) {
            $scope.adminConsumerSelect = args.consumerToList;
        });

        $scope.editConsumer = function(id){
            $scope.fastEditConsumer = true;
            $scope.editConsumerItem = id;

            $scope.consumerList.$promise.then(function(data){
                angular.forEach(data, function(value, key) {
                    if(id === value.idConsumer){
                        $scope.consumerName = value.name;
                    }
                });
            });



            $scope.$on("saveEditConsumerBroadcast", function (event, args) {
                editableName = args.saveConsumerBroadcast;
            });
        };

        $scope.saveNewConsumer = function(id, name){
            if( name === null ||
                name === '' ||
                name === 0 ||
                name === NaN ||
                angular.isUndefined(name)){
                alert("Введите корректные данные!");
            } else {
                var consumer = {
                    "idConsumer": id,
                    "name": name
                };
                saveConsumerFactory.save(consumer,
                    function (resp, headers) {
                        //success callback
                        $scope.consumerList = getAllConsumersFactory.query();
                        $scope.nameConsumer = null;
                        $scope.fastEditConsumer = false;
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    });
            }
        };

        $scope.saveEditableConsumer = function(id){
            var consumer = {
                "idConsumer": id,
                "name": editableName
            };
            saveConsumerFactory.save(consumer,
                function (resp, headers) {
                    //success callback
                    $scope.consumerList = getAllConsumersFactory.query();
                    $scope.nameConsumer = null;
                    $scope.fastEditConsumer = false;
                },
                function (err) {
                    // error callback
                    alert(err.statusText);
                });
        };

        $scope.cancelConsumer = function(consumerName){
            $scope.fastEditConsumer = false;
            $scope.nameConsumer = null;
            $rootScope.$broadcast('cancelEditConsumerBroadcast',{
                editBroadcast: consumerName
            })
        };

        $scope.deleteConsumer = function(id, name){
            var result = confirm('Удалить \"'+name+'\" ?');
            if(result){
                deleteConsumerFactory.delete({id:id},
                    function (resp, headers) {
                        //success callback
                        $scope.consumerList = getAllConsumersFactory.query();
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    }
                );
            }
        };

    })

    .controller('secondConsumerCtrl', function($rootScope, $scope){
        $scope.$on('cancelEditConsumerBroadcast', function (event, args) {
            $scope.consumerName = args.editBroadcast;
        });

        $scope.$watch('consumerName', function () {
            $rootScope.$broadcast('saveEditConsumerBroadcast',{
                saveConsumerBroadcast: angular.element('#consumerName').val()
            });
        });

    });

