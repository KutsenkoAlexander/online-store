angular.module('monolitApp.customers', ['ui.router'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('customers', {
                url: '/customers',
                views: {
                    'content': {
                        templateUrl: 'customers/customers.html',
                        controller: 'customersCtrl'
                    }
                },
                data: {
                    pageTitle: 'Покупателям'
                }
            });

    })

    .controller('customersCtrl', function ($rootScope, $scope, $location, pageFactory, pageCacheFactory) {
        $rootScope.$broadcast('page', {
            currentUrl: '/customers',
            name: 'Покупателям',
            nesting: 2
        });

        $scope.dataPage = pageCacheFactory.get($location.$$url.substr(1));
        if(!$scope.dataPage){
            $scope.dataPage = pageFactory.query({url: $location.$$url.substr(1)});
            pageCacheFactory.put($location.$$url.substr(1), $scope.dataPage);
        }

    });