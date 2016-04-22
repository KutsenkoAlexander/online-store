angular.module('monolitApp.career', ['ui.router'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('career', {
                url: '/career',
                views: {
                    'content': {
                        templateUrl: 'career/career.html',
                        controller: 'careerCtrl'
                    }
                },
                data: {
                    pageTitle: 'Карьера'
                }
            });

    })

    .controller('careerCtrl', function ($rootScope, $scope, $location, pageFactory, pageCacheFactory) {
        $rootScope.$broadcast('page', {
            currentUrl: '/career',
            name: 'Карьера',
            nesting: 2
        });

        $scope.dataPage = pageCacheFactory.get($location.$$url.substr(1));
        if(!$scope.dataPage){
            $scope.dataPage = pageFactory.query({url: $location.$$url.substr(1)});
            pageCacheFactory.put($location.$$url.substr(1), $scope.dataPage);
        }

    });