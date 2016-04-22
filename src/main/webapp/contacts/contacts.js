angular.module('monolitApp.contacts', ['ui.router'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('contacts', {
                url: '/contacts',
                views: {
                    'content': {
                        templateUrl: 'contacts/contacts.html',
                        controller: 'contactsCtrl'
                    }
                },
                data: {
                    pageTitle: 'Контакты'
                }
            });

    })

    .controller('contactsCtrl', function ($rootScope, $scope, $location, pageFactory, pageCacheFactory) {
        $rootScope.$broadcast('page', {
            currentUrl: '/contacts',
            name: 'Контакты',
            nesting: 2
        });

        $scope.dataPage = pageCacheFactory.get($location.$$url.substr(1));
        if(!$scope.dataPage){
            $scope.dataPage = pageFactory.query({url: $location.$$url.substr(1)});
            pageCacheFactory.put($location.$$url.substr(1), $scope.dataPage);
        }
    });