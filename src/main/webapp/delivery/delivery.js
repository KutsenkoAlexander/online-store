angular.module('monolitApp.delivery', ['ui.router'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('delivery', {
                url: '/delivery',
                views: {
                    'content': {
                        templateUrl: 'delivery/delivery.html',
                        controller: 'deliveryCtrl'
                    }
                },
                data: {
                    pageTitle: 'Доставка'
                }
            });

    })

    .controller('deliveryCtrl', function ($rootScope, $scope, $location, pageFactory, pageCacheFactory) {
        $rootScope.$broadcast('page', {
            currentUrl: '/delivery',
            name: 'Доставка',
            nesting: 2
        });

        $scope.dataPage = pageCacheFactory.get($location.$$url.substr(1));
        if(!$scope.dataPage){
            $scope.dataPage = pageFactory.query({url: $location.$$url.substr(1)});
            pageCacheFactory.put($location.$$url.substr(1), $scope.dataPage);
        }

    });