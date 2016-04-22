angular.module('monolitApp.home', ['ui.router'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                url: '/',
                views: {
                    'content': {
                        templateUrl: 'home/home.html',
                        controller: 'homeCtrl'
                    }
                },
                data: {
                    pageTitle: 'Монолит'
                }
            });

    })

    .controller('homeCtrl', function ($rootScope, $scope, $location, pageFactory, pageCacheFactory) {
        $rootScope.$broadcast('page', {
            currentUrl: '/',
            name: 'Монолит',
            nesting: 2
        });

        $scope.dataPage = pageCacheFactory.get("home");
        if(!$scope.dataPage){
            $scope.dataPage = pageFactory.query({url: "home"});
            pageCacheFactory.put("home", $scope.dataPage);
        }

    });