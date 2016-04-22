angular.module('monolitApp.sale', ['ui.router'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('sale', {
                url: '/sale',
                views: {
                    'content': {
                        templateUrl: 'sale/sale.html',
                        controller: 'saleCtrl'
                    }
                },
                data: {
                    pageTitle: 'Продажа'
                }
            });

    })

    .controller('saleCtrl', function ($rootScope, $scope, $location, pageFactory, pageCacheFactory) {
        $rootScope.$broadcast('page', {
            currentUrl: '/sale',
            name: 'Продажа',
            nesting: 2
        });

        $scope.dataPage = pageCacheFactory.get($location.$$url.substr(1));
        if(!$scope.dataPage){
            $scope.dataPage = pageFactory.query({url: $location.$$url.substr(1)});
            pageCacheFactory.put($location.$$url.substr(1), $scope.dataPage);
        }

    });