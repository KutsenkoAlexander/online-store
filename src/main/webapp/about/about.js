angular.module('monolitApp.about', ['ui.router'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('about', {
                url: '/about',
                views: {
                    'content': {
                        templateUrl: 'about/about.html',
                        controller: 'aboutCtrl'
                    }
                },
                data: {
                    pageTitle: 'О нас'
                }
            });

    })

    .controller('aboutCtrl', function ($rootScope, $scope, $location, pageFactory, pageCacheFactory) {
        $rootScope.$broadcast('page', {
            currentUrl: '/about',
            name: 'О нас',
            nesting: 2
        });

        $scope.dataPage = pageCacheFactory.get($location.$$url.substr(1));
        if(!$scope.dataPage){
            $scope.dataPage = pageFactory.query({url: $location.$$url.substr(1)});
            pageCacheFactory.put($location.$$url.substr(1), $scope.dataPage);
        }

    });