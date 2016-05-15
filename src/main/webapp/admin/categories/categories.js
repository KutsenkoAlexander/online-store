angular.module('monolitApp.admin.categories', ['ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.categories', {
                url: "/categories",
                views: {
                    'admin': {
                        templateUrl: "admin/categories/categories.html",
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

    .factory('getCategoryByIdFactory', function ($resource) {
        return $resource('/catalog/category/name/:id', {id: '@id'},{'query': { method: 'GET' }});
    })

    .factory('saveCategoryFactory', function($resource){
        return $resource('/admin/category/save', {},{'save': {method:'POST'}});
    })

    .controller('adminCategoryCtrl', function($scope, $rootScope,
                                              allParentCategoryFactory,
                                              allChildCategoryFactory,
                                              saveCategoryFactory,
                                              Upload,
                                              $timeout,
                                              pageCacheFactory,
                                              getCategoryByIdFactory){
        $scope.categories = allChildCategoryFactory.query();

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

        $scope.$on("categoryBroadcastToList", function (event, args) {
            $scope.addGoodsCategory = args.categoryToList;
        });

        $scope.$on("isCategoryAddBroadcast", function (event, args) {
            $scope.isCategoryAdd = true;
            $scope.parenCategories = pageCacheFactory.get('parenCategories');
            if (!$scope.parenCategories) {
                $scope.parenCategories = allParentCategoryFactory.query();
                pageCacheFactory.put('parenCategories', $scope.parenCategories);
            }
        });

        $scope.selectCategoryFromList = function(addGoodsCategory){
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

        $scope.cancelAddCategory = function(){
            $scope.isCategoryAdd = false;
            $scope.isCategoryEdit = false;
            $scope.category = null;
            $scope.name_category = null;
            $scope.f = null;
            $scope.img_category = null;
            $scope.savedImg = null;
        };

        $scope.saveCategory = function(name, parentId, idCategory){
            var category = {
                "idCategory": idCategory,
                "name": name,
                "parentId": parentId,
                "image": $scope.savedImg
            };
            saveCategoryFactory.save(category,
                function (resp, headers) {
                    //success callback
                    $scope.categories = allChildCategoryFactory.query();
                    $scope.isCategoryAdd = false;
                    $scope.isCategoryEdit = false;
                    $scope.name_category = '';
                    $scope.f = null;
                    $scope.img_category = null;
                    $scope.savedImg = null;
                },
                function (err) {
                    // error callback
                    confirm("Ошибка сохранения категории!");
                });
        };

        $scope.editCategory = function(id){
            $scope.isCategoryEdit = true;
            $scope.idCategoryForSave = id;
            var editableCategory = getCategoryByIdFactory.query({id:id});
            editableCategory.$promise.then(function(data){
                $scope.parenCategories = pageCacheFactory.get('parenCategories');
                if(!$scope.parenCategories){
                    $scope.parenCategories = allParentCategoryFactory.query();
                    pageCacheFactory.put('parenCategories', $scope.parenCategories);
                }

                $scope.parenCategories.$promise.then(function(parentCategoryData){
                    angular.forEach(parentCategoryData, function(value, key) {
                        if(value.idCategory === data.parentId){
                            $scope.category = $scope.parenCategories[key];
                        }
                    });
                });
                $scope.savedImg = data.image;
                $scope.name_category = data.name;
            });
        }
    });
