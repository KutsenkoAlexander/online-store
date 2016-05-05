angular.module('monolitApp.breadcrumbs', [])

    .factory('categoryNameByIdFactory', function($resource){
        return $resource('/catalog/category/name/:id', {id: '@id'},{'query': { method: 'GET' }});
    })
    
    .factory('breadcrumbsCacheFactory', function ($cacheFactory) {
        return $cacheFactory('breadcrumbsCache', {});
    })

    .controller('breadcrumbsCtrl', function ($scope) {
        $scope.$on('page', function (event, data) {
            $scope.stateNavigation = [];
            if(data.currentUrl !== '/') {
                //refresh a page catalog
                if($scope.stateNavigation.length === 0 && data.nesting === 2) {
                    $scope.stateNavigation.push({
                        url: data.currentUrl, name: data.name, nesting: data.nesting
                    });
                }
                //refresh a page category
                if ($scope.stateNavigation.length === 0 && data.nesting === 3) {
                    $scope.stateNavigation.push({
                        url: '/catalog',
                        name: 'Каталог',
                        nesting: 2
                    });
                }
                //refresh a page list goods
                if ($scope.stateNavigation.length === 0 && data.nesting === 4) {
                    $scope.stateNavigation.push(
                        {url: '/catalog', name: 'Каталог', nesting: 2},
                        {url: '/catalog/'+data.category, name: data.categoryName, nesting: 3}
                    );
                }
                //refresh a page single good
                if ($scope.stateNavigation.length === 0 && data.nesting === 5) {
                    $scope.stateNavigation.push(
                        {url: '/catalog', name: 'Каталог', nesting: 2},
                        {url: '/catalog/'+data.category, name: data.categoryName, nesting: 3},
                        {url: '/catalog/products/'+data.productList, name: data.productListName, nesting: 4}
                    );
                }
                //otherwise
                if ($scope.stateNavigation.length > 0 && data.nesting === 5) {
                        $scope.stateNavigation.push({url: data.currentUrl, name: data.name, nesting: data.nesting});
                }
                if ($scope.stateNavigation.length > 0 && data.nesting === 4) {
                        $scope.stateNavigation.push({url: data.currentUrl, name: data.name, nesting: data.nesting});
                }
                if ($scope.stateNavigation.length > 0 && data.nesting === 3) {
                        $scope.stateNavigation.push({url: data.currentUrl, name: data.name, nesting: data.nesting});
                }
            }
        });
    });
