angular.module('monolitApp.category', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('category', {
                url: '/catalog/:category',
                views: {
                    'content': {
                        templateUrl: 'category/category.html',
                        controller: 'categoryCtrl'
                    }
                },
                data: {
                    pageTitle: 'Категории'
                }
            });

    })

    .factory('categoryFactory', function ($resource) {
        return $resource('/catalog/category/child_with_img/:id', {id: '@id'});
    })
    
    .factory('categoryCacheFactory', function ($cacheFactory) {
        return $cacheFactory('categoryCache', {});
    })

    .controller('categoryCtrl', function ($scope,
                                          $rootScope,
                                          categoryFactory,
                                          $stateParams,
                                          categoryNameByIdFactory,
                                          categoryCacheFactory,
                                          breadcrumbsCacheFactory) {
        //breadcrumbs
        var productParentId = breadcrumbsCacheFactory.get('categoriesBreadcrumbs'+$stateParams.category);
        if (!productParentId) {
            productParentId = categoryNameByIdFactory.query({id: $stateParams.category});
            breadcrumbsCacheFactory.put('categoriesBreadcrumbs'+$stateParams.category, productParentId);
        }
        productParentId.$promise.then(function (data) {
            $rootScope.$broadcast('page', {
                currentUrl: '/catalog/' + data.idCategory,
                name: data.name,
                nesting: 3
            });
        });
        //content
        $scope.categories = categoryCacheFactory.get('categories'+$stateParams.category);
        if(!$scope.categories) {
            $scope.categories = categoryFactory.query({id: $stateParams.category});
            categoryCacheFactory.put('categories'+$stateParams.category, $scope.categories);
        }
    });