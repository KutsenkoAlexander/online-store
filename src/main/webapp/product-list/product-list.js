angular.module('monolitApp.productList', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('productList', {
                url: '/catalog/products/:productList',
                views: {
                    'content': {
                        templateUrl: 'product-list/product-list.html',
                        controller: 'productListCtrl'
                    }
                },
                data: {
                    pageTitle: 'Список товаров'
                }
            });
    })

    .factory('productsByCatIdFactory', function ($resource) {
        return $resource('/product/category/:id', {id: '@id'},{'query': { method: 'GET', isArray:false}});
    })

    .factory('productListCacheFactory', function ($cacheFactory) {
        return $cacheFactory('productListCache', {});
    })

    .controller('productListCtrl', function($scope,
                                            $rootScope,
                                            $location,
                                            $stateParams,
                                            productsByCatIdFactory,
                                            categoryNameByIdFactory,
                                            paramsFactory,
                                            breadcrumbsCacheFactory,
                                            productListCacheFactory) {
        var search = $location.search();
        paramsFactory.id = $stateParams.productList;
        paramsFactory.page = search.page || 0;
        paramsFactory.size = 21;//items on page
        paramsFactory.consumerId = search.consumerId || null;
        paramsFactory.typeId = search.typeId || null;
        paramsFactory.sizeId = search.sizeId || null;
        paramsFactory.colorId = search.colorId || null;
        paramsFactory.price = search.price || null;
        paramsFactory.exist = search.exist || null;
        paramsFactory.sort = null;
        //sorting
        var atributes = [];
        angular.forEach($location.search(), function(data){
            atributes.push(data);
        });
        if(atributes.length === 0 || atributes.length >= 1 ){
            $scope.$on("paginationProducts", function(event, args){
                $scope.products = args.products;
                $scope.page = args.page;
            });
        }
        $scope.$on("filterEvent", function(event, args){
            angular.element( document.querySelector( 'body' )).removeClass('loadResponse');
            $scope.products = args.products;
            $scope.page = args.page;
        });
        //product list
        $scope.productListByCatId = productListCacheFactory.get('product'+$stateParams.productList);
        if(!$scope.productListByCatId){
            $scope.productListByCatId = categoryNameByIdFactory.query({id: $stateParams.productList});
            productListCacheFactory.put('product'+$stateParams.productList, $scope.productListByCatId);
        }
        //breadcrumbs
        $scope.productListByCatId.$promise.then(function (data) {
            $scope.name = data.name;
            $scope.parentId = data.parentId;
            if (data.parentId !== 0) {
                $scope.productCategoryByParentId = breadcrumbsCacheFactory.get('categoriesBreadcrumbs'+$scope.parentId);
                if(!$scope.productCategoryByParentId){
                    $scope.productCategoryByParentId = categoryNameByIdFactory.query({id: $scope.parentId});
                    breadcrumbsCacheFactory.put('categoriesBreadcrumbs'+$scope.parentId, $scope.productCategoryByParentId);
                }
                $scope.productCategoryByParentId.$promise.then(function (data) {
                    $scope.categoryName = data.name;
                    $rootScope.$broadcast('page', {
                        currentUrl: '/catalog/products/' + data.idCategory,
                        name: $scope.name,
                        nesting: 4,
                        category: $scope.parentId,
                        categoryName: $scope.categoryName
                    });
                });
            }
        });
    });