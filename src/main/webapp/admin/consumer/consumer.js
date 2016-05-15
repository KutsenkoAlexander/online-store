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
            $scope.consumerName = $scope.consumerList[id-1].name;
        };

        $scope.saveConsumer = function(id, name){
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
                console.log(consumer);
                saveConsumerFactory.save(consumer,
                    function (resp, headers) {
                        //success callback
                        $scope.consumerList = getAllConsumersFactory.query();
                        $scope.nameConsumer = null;
                        $scope.fastEditConsumer = false;
                    },
                    function (err) {
                        // error callback
                        confirm("Ошибка сохранения производителя!");
                    });
            }
        };

        $scope.cancelConsumer = function(){
            $scope.fastEditConsumer = false;
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

        $scope.$watch('consumerName', function () {
            $scope.consumerNameTest = angular.element("#consumerName").val();
        });

    });
