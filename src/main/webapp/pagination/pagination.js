angular.module('monolitApp.pagination', ['ngResource','monolitApp.sort'])

    .factory('pageQueryFactory',function($resource){
        return $resource('/product/category/:id', {id: '@id'}, {'query': {method: 'GET', isArray:false}});
    })


    .controller('paginationCtrl', function ($scope, $rootScope, $location, $stateParams, pageQueryFactory, paramsFactory, sortFactory) {
        if ($location.search().consumerId !== null ||
            $location.search().typeId !== null ||
            $location.search().sizeId !== null ||
            $location.search().colorId !== null ||
            $location.search().price !== null ||
            $location.search().exist) {
            $scope.$on('filterEvent', function (event, args) {
                $scope.products = args.content;
                $scope.page = args.page;
                $scope.idCategoryProducts = paramsFactory.id;
                angular.forEach(args.links, function (value) {
                    if (value.rel === 'next') {
                        $scope.nextPageLink = value.href;
                    }
                    if (value.rel === 'prev') {
                        $scope.prevPageLink = value.href;
                    }
                });
            });
        }
        $scope.setPageAndSize = function(page){
            paramsFactory.page = page;
            $location.search("page", page || null);
            sortFactory.query(paramsFactory).$promise.then(function (data) {
                $rootScope.$broadcast("paginationProducts", {
                    products: data.content
                });
                $scope.page = data.page;
                $scope.idCategoryProducts = paramsFactory.id;
                angular.forEach(data.links, function (value) {
                    if (value.rel === 'next') {
                        $scope.nextPageLink = value.href;
                    }
                    if (value.rel === 'prev') {
                        $scope.prevPageLink = value.href;
                    }
                });
            })
        };
    })
    
    .filter('pages', function () {
        return function (input, totalPages) {
            totalPages = parseInt(totalPages);
            for (var i = 0; i <= totalPages-1; i++) {
                input.push(i);
            }
            return input;
        };
    });
