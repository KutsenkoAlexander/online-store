angular.module('monolitApp.admin.categories', ['ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.categories', {
                url: "/categories",
                views: {
                    'admin': {
                        templateUrl: "admin/categories/edit.category.html",
                        controller: 'adminCategoryCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление категориями'
                }
            })
    })

    .factory('allChildCategoryFactory', function ($resource) {
        return $resource('/catalog/category/child', {}, {});
    })

    .factory('allParentCategoryFactory', function ($resource) {
        return $resource('/catalog/category/parent', {}, {});
    })

    .factory('saveCategoryFactory', function($resource){
        return $resource('/catalog/category/save', {},{'save': {method:'POST'}});
    })

    .controller('adminCategoryCtrl', function($scope, $rootScope,
                                              allParentCategoryFactory,
                                              allChildCategoryFactory,
                                              saveCategoryFactory,
                                              Upload,
                                              $timeout,
                                              pageCacheFactory){
        var imgId;

        $scope.categories = allChildCategoryFactory.query();

        $scope.$on("categoryBroadcastToList", function (event, args) {
            $scope.addGoodsCategory = args.categoryToList;
        });
        
        $scope.selectCategoryFromList = function(addGoodsCategory){
            $rootScope.$broadcast('catBroadcast', {
                selectCat: addGoodsCategory
            });
        };

        $scope.$on("isCategoryAddBroadcast", function (event, args) {
            $scope.isCategoryAdd = true;
            $scope.parenCategories = pageCacheFactory.get('parenCategories');
            if (!$scope.parenCategories) {
                $scope.parenCategories = allParentCategoryFactory.query();
                pageCacheFactory.put('parenCategories', $scope.parenCategories);
            }
        });

        //when product add or edit
        $scope.selectCat = function(addGoodsCategory){
            $rootScope.$broadcast('catBroadcast', {
                selectCat: addGoodsCategory
            });
        };

        $scope.addCategory = function(){
            $scope.isCategoryAdd = true;
            $scope.parenCategories = pageCacheFactory.get('parenCategories');
            if(!$scope.parenCategories){
                $scope.parenCategories = allParentCategoryFactory.query();
                pageCacheFactory.put('parenCategories', $scope.parenCategories);
            }
        };

        $scope.uploadFiles = function(file, errFiles) {
            $scope.img_category = file;
            $scope.errFile = errFiles && errFiles[0];
            if (file) {
                file.upload = Upload.upload({
                    url: '/image/save',
                    data: {file: file}
                });

                file.upload.then(function (response) {
                    $scope.savedImg = response.data;
                    $timeout(function () {
                        file.result = response.data;
                    });
                }, function (response) {
                    if (response.status > 0)
                        $scope.errorMsg = response.status + ': ' + response.data;
                }, function (evt) {
                    file.progress = Math.min(100, parseInt(100.0 *
                        evt.loaded / evt.total));
                });
            }
        };

        $scope.cancelAddCategory = function(){
            $scope.isCategoryAdd = false;
            $scope.name_category = '';
            $scope.f = null;
            $scope.img_category = null;
            $scope.savedImg = null;
        };

        $scope.saveCategory = function(name, parentId){
            var category = {
                "name": name,
                "parentId": parentId,
                "image": $scope.savedImg
            };
            saveCategoryFactory.save(category);
            $scope.categories = allChildCategoryFactory.query();
            $scope.isCategoryAdd = false;
            $scope.name_category = '';
            $scope.f = null;
            $scope.img_category = null;
            $scope.savedImg = null;
        };

    });
