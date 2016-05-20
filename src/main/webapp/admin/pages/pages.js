angular.module('monolitApp.admin.page', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.pages', {
                url: "/pages",
                views: {
                    'admin': {
                        templateUrl: "admin/pages/pages.html",
                        controller: 'adminPagesCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление страницами'
                }
            })
    })

    .factory('pagesEditFactory', function ($resource) {
        return $resource('/page/all', {},{});
    })

    .factory('getPageFactory', function ($resource) {
        return $resource('/page/single/:id', {id: '@id'},{'query': { method: 'GET', isArray:false}});
    })

    .factory('savePageFactory', function ($resource) {
        return $resource('/rest/page/save', {},{'save': {method:'POST'}});
    })

    .controller('adminPagesCtrl', function ($rootScope,
                                            $scope,
                                            $location,
                                            pageFactory,
                                            pageCacheFactory,
                                            pagesEditFactory,
                                            getPageFactory,
                                            savePageFactory) {
        $scope.dataPage= pageCacheFactory.get('editablePages');
        if(!$scope.dataPage){
            $scope.dataPage = pagesEditFactory.query();
            pageCacheFactory.put('editablePages', $scope.dataPage);
        }

        $scope.editPage = function (index) {
            $scope.selectedPage = getPageFactory.query({id: index});
            $scope.selectedPage.$promise.then(function (data) {
                    $scope.page_content = data.content;
                }
            );
            $scope.isEditable = true;
        };

        $scope.savePage = function (id, content) {
            var con = content;
            if(angular.isUndefined(content)){
                con = $scope.selectedPage.content;
            }
            var page = {"id":id, "url":$scope.selectedPage.url, "description":$scope.selectedPage.description, "content": content};
            savePageFactory.save(page);
            $scope.dataPage = pagesEditFactory.query();
            $scope.isEditable = false;
        };

        $scope.cancelPage = function () {
            $scope.dataPage = pagesEditFactory.query();
            $scope.isEditable = false;
        };
    });