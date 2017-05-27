angular.module('monolitApp.sort', ['ui.router', 'ngResource'])

    .factory('consumerFactory', function($resource){
        return $resource('/consumer/:id', {id: '@id'},{});
    })

    .factory('typeFactory', function($resource){
        return $resource('/type/:id', {id: '@id'},{});
    })

    .factory('sizeFactory', function($resource){
        return $resource('/size/:id', {id: '@id'},{});
    })

    .factory('colorFactory', function($resource){
        return $resource('/color/:id', {id: '@id'},{});
    })

    .factory('sortFactory', function($resource){
        // return $resource('http://monolit-kutsenko.rhcloud.com/product/sort/:id', {id: '@id'}, {'query': {method: 'GET', isArray:false}});
        return $resource('product/sort/:id', {id: '@id'}, {'query': {method: 'GET', isArray:false}});
    })

    .factory('sortCacheFactory', function ($cacheFactory) {
        return $cacheFactory('sortCache', {});
    })

    .factory('paramsFactory',function(){
        var param = {};
        return param;
    })

    .controller('sortCtrl', function($scope,
                                     $rootScope,
                                     $stateParams,
                                     consumerFactory,
                                     typeFactory,
                                     sizeFactory,
                                     colorFactory,
                                     sortFactory,
                                     paramsFactory,
                                     sortCacheFactory,
                                     $location){
        $scope.sortConsumers = [];
        $scope.sortTypes = [];
        $scope.sortSizes = [];
        $scope.sortColors = [];
        $scope.sortPrice = [{ value: 1, name: 'Дешёвые' },{ value: 2, name: 'Дорогие' }];

        var getResultSortQuery = function (params) {
            sortFactory.query(params).$promise.then(function(data){
                $rootScope.$broadcast("filterEvent", {
                    products: data.content,
                    page: data.page,
                    links: data.links
                });
            });
        };

        //consumer select
        var cacheConsumers = sortCacheFactory.get('consumers'+$stateParams.productList);
        if(!cacheConsumers){
            cacheConsumers = consumerFactory.query({id: $stateParams.productList});
            sortCacheFactory.put('consumers'+$stateParams.productList, cacheConsumers);
        }
        cacheConsumers.$promise.then(function(data){
            angular.forEach(data, function(item){
                $scope.sortConsumers.push(item);
                $scope.sortConsumers = _.sortBy($scope.sortConsumers, 'name', 'default');
            });
            if ($location.search().consumerId === null){
                $scope.consumer = $scope.sortConsumers[0];
            }else{
                angular.forEach($scope.sortConsumers, function (item) {
                    if (angular.isDefined(item.idConsumer)) {
                        if (item.idConsumer == $location.search().consumerId) {
                            $scope.consumer = item;
                            paramsFactory.id = $stateParams.productList;
                            paramsFactory.consumerId = $location.search().consumerId;
                        }
                    }
                });
            }
        });

        $scope.selectedConsumer = function(idConsumer){
            paramsFactory.id = $stateParams.productList;
            if($scope.consumer != null){
                paramsFactory.consumerId = $scope.consumer.idConsumer;
            }else{
                paramsFactory.consumerId = null;
            }
            paramsFactory.page = null;
            $location.search("page", null);
            $location.search("consumerId", idConsumer);
            if(!getResultSortQuery(paramsFactory)){
                angular.element( document.querySelector( 'body' )).addClass('loadResponse');
            }
            getResultSortQuery(paramsFactory);
        };

        //type select
        var cacheType = sortCacheFactory.get('type'+$stateParams.productList);
        if(!cacheType){
            cacheType = typeFactory.query({id: $stateParams.productList});
            sortCacheFactory.put('type'+$stateParams.productList, cacheType);
        }
        cacheType.$promise.then(function(data){
            angular.forEach(data, function(item){
                $scope.sortTypes.push(item);
                $scope.sortTypes = _.sortBy($scope.sortTypes, 'name', 'default');
            });
            if($location.search().typeId === null){
                $scope.type = $scope.sortTypes[0];
            }else{
                angular.forEach($scope.sortTypes, function (item) {
                    if (angular.isDefined(item.idSprType)) {
                        if (item.idSprType == $location.search().typeId) {
                            $scope.type = item;
                            paramsFactory.id = $stateParams.productList;
                            paramsFactory.typeId = $scope.type.idSprType || null;
                        }
                    }
                });
            }
        });
        $scope.selectedType = function(idType) {
            paramsFactory.id = $stateParams.productList;
            if($scope.type != null) {
                paramsFactory.typeId = $scope.type.idSprType || null;
            }else{
                paramsFactory.typeId = null;
            }
            paramsFactory.page = null;
            $location.search("page", null);
            $location.search("typeId", idType);
            if(!getResultSortQuery(paramsFactory)){
                angular.element( document.querySelector( 'body' )).addClass('loadResponse');
            }
            getResultSortQuery(paramsFactory);
        };

        //size select
        var cacheSize = sortCacheFactory.get('size'+$stateParams.productList);
        if(!cacheSize){
            cacheSize = sizeFactory.query({id: $stateParams.productList});
            sortCacheFactory.put('size'+$stateParams.productList, cacheSize);
        }
        cacheSize.$promise.then(function(data){
            angular.forEach(data, function(item){
                $scope.sortSizes.push(item);
                $scope.sortSizes = _.sortBy($scope.sortSizes, 'name', 'default');
            });
            if($location.search().sizeId === null){
                $scope.size = $scope.sortSizes[0];
            }else{
                angular.forEach($scope.sortSizes, function (item) {
                    if (angular.isDefined(item.idSprSize)) {
                        if (item.idSprSize == $location.search().sizeId) {
                            $scope.size = item;
                            paramsFactory.id = $stateParams.productList;
                            paramsFactory.sizeId = $scope.size.idSprSize || null;
                        }
                    }
                });
            }
        });
        $scope.selectedSize = function(idSize){
            paramsFactory.id = $stateParams.productList;
            if($scope.size != null) {
                paramsFactory.sizeId = $scope.size.idSprSize;
            }else{
                paramsFactory.sizeId = null;
            }
            paramsFactory.page = null;
            $location.search("page", null);
            $location.search("sizeId", idSize);
            if(!getResultSortQuery(paramsFactory)){
                angular.element( document.querySelector( 'body' )).addClass('loadResponse');
            }
            getResultSortQuery(paramsFactory);
        };

        //color select
        var cacheColor = sortCacheFactory.get('color'+$stateParams.productList);
        if(!cacheColor){
            cacheColor = colorFactory.query({id: $stateParams.productList});
            sortCacheFactory.put('color'+$stateParams.productList, cacheColor);
        }
        cacheColor.$promise.then(function(data){
            angular.forEach(data, function(item){
                $scope.sortColors.push(item);
                $scope.sortColors = _.sortBy($scope.sortColors, 'name', 'default');
            });
            if($location.search().colorId === null){
                $scope.color = $scope.sortColors[0];
            }else{
                angular.forEach($scope.sortColors, function (item) {
                    if (angular.isDefined(item.idSprColors)) {
                        if (item.idSprColors == $location.search().colorId) {
                            $scope.color = item;
                            paramsFactory.id = $stateParams.productList;
                            paramsFactory.colorId = $scope.color.idSprColors || null;
                        }
                    }
                });
            }
        });
        $scope.selectedColor = function(idColor){
            paramsFactory.id = $stateParams.productList;
            if($scope.color != null) {
                paramsFactory.colorId = $scope.color.idSprColors;
            }else{
                paramsFactory.colorId = null;
            }
            paramsFactory.page = null;
            $location.search("page", null);
            $location.search("colorId", idColor);
            if(!getResultSortQuery(paramsFactory)){
                angular.element( document.querySelector( 'body' )).addClass('loadResponse');
            }
            getResultSortQuery(paramsFactory);
        };

        //price select
        angular.forEach($scope.sortPrice, function(item){
            if(angular.isDefined(item.value)) {
                if (item.value == $location.search().price) {
                    $scope.price = item;
                    paramsFactory.id = $stateParams.productList;
                    switch (item.value){
                        case 1:
                            paramsFactory.sort = 'price,asc';
                            break;
                        case 2:
                            paramsFactory.sort = 'price,desc';
                            break;
                        default:
                            paramsFactory.sort = null;
                            paramsFactory.price = null;
                            paramsFactory.page = null;
                            $location.search("page", null);
                            break;
                    }
                }
            }
        });
        $scope.selectedPrice = function(price){
            $location.search("price", price);
            paramsFactory.id = $stateParams.productList;
            switch (price){
                case 1:
                    paramsFactory.sort = 'price,asc';
                    if(!getResultSortQuery(paramsFactory)){
                        angular.element( document.querySelector( 'body' )).addClass('loadResponse');
                    }
                    getResultSortQuery(paramsFactory);
                    break;
                case 2:
                    paramsFactory.sort = 'price,desc';
                    if(!getResultSortQuery(paramsFactory)){
                        angular.element( document.querySelector( 'body' )).addClass('loadResponse');
                    }
                    getResultSortQuery(paramsFactory);
                    break;
                default:
                    paramsFactory.sort = null;
                    paramsFactory.price = null;
                    paramsFactory.page = null;
                    $location.search("page", null);
                    if(!getResultSortQuery(paramsFactory)){
                        angular.element( document.querySelector( 'body' )).addClass('loadResponse');
                    }
                    getResultSortQuery(paramsFactory);
                    break;
            }
        };

        //exist select
        if ($location.search().exist) {
            $scope.exist = $location.search().exist;
            $('.chk').prop("checked");
            paramsFactory.id = $stateParams.productList;
            paramsFactory.exist = 1;
        }
        $scope.isExist = function(exist){
            paramsFactory.id = $stateParams.productList;
            if(exist){
                paramsFactory.exist = 1;
                paramsFactory.page = null;
                $location.search("page", null);
            }else{
                paramsFactory.exist = null;
                exist = null;
            }
            $location.search("exist", exist);
            if(!getResultSortQuery(paramsFactory)){
                angular.element( document.querySelector( 'body' )).addClass('loadResponse');
            }
            getResultSortQuery(paramsFactory);
        };

        //reset all filters
        $scope.resetFilter = function () {
            $('.chk').prop("checked", false);
            paramsFactory.id = $stateParams.productList;

            paramsFactory.consumerId = null;
            $scope.consumer = '';
            $location.search("consumerId", null);

            paramsFactory.typeId = null;
            $scope.type = '';
            $location.search("typeId", null);

            paramsFactory.sizeId = null;
            $scope.size = '';
            $location.search("sizeId", null);

            paramsFactory.colorId = null;
            $scope.color = '';
            $location.search("colorId", null);

            paramsFactory.sort = null;
            paramsFactory.price = null;
            $scope.price = '';
            $location.search("price", null);

            paramsFactory.exist = null;
            $scope.exist = false;
            $location.search("exist", null);

            paramsFactory.page = null;
            $location.search("page", null);

            if(!getResultSortQuery(paramsFactory)){
                angular.element( document.querySelector( 'body' )).addClass('loadResponse');
            }

            getResultSortQuery(paramsFactory);
        };

        getResultSortQuery(paramsFactory);
    });
