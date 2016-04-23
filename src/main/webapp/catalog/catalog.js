angular.module('monolitApp.catalog', ['ui.router','ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('catalog', {
                url: '/catalog',
                views: {
                    'content': {
                        templateUrl: 'catalog/catalog.html',
                        controller: 'catalogCtrl'
                    }
                },
                data: {
                    pageTitle: 'Каталог'
                }
            });

    })

    .factory('catFactory', function($resource) {
        return $resource('/catalog/category/parent_with_img');
    })

    .factory('catalogCacheFactory', function ($cacheFactory) {
        return $cacheFactory('catalogCache', {});
    })

    .controller('catalogCtrl', function($rootScope, $scope, catFactory, catalogCacheFactory) {
        $rootScope.$broadcast('page', {
            currentUrl: '/catalog',
            name: 'Каталог',
            nesting: 2
        });
        $scope.catalog = catalogCacheFactory.get('catalog');
        if(!$scope.catalog){
            $scope.catalog = catFactory.query();
            catalogCacheFactory.put('catalog', $scope.catalog);
        }
    });