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

    .factory('saveCategoryFactory', function($resource){
        return $resource('/catalog/category/save', {},{'save': {method:'POST'}});
    })

    .controller('adminCategoryCtrl', function($scope,
                                              $rootScope,
                                              allParentCategoryFactory,
                                              allChildCategoryFactory,
                                              saveCategoryFactory,
                                              Upload,
                                              $timeout,
                                              pageCacheFactory){

        $scope.$on("categoryBroadcastToList", function (event, args) {
            $scope.addGoodsCategory = args.categoryToList;
        });

        $scope.$on("itemsBroadcast", function (event, args) {
            //$scope.savedIdCategoryImg = null;
            //$scope.img_product = null;
            console.log("qwerty");
            $scope.addGoodsCode = null;
            $scope.$watch('addGoodsCode', function(){
                angular.element('#addGoodsCode').val('');
            });

            $scope.name_product = null;
            $scope.$watch('name_product', function(){
                angular.element('#name_product').val('');
            });

            $scope.description = null;
            $scope.$watch('description', function(){
                angular.element('#description').val('');
            });

            $scope.priceUah = null;
            $scope.$watch('priceUah', function(){
                angular.element('#priceUah').val('');
            });

            $scope.priceCent = null;
            $scope.$watch('priceCent', function(){
                angular.element('#priceCent').val('');
            });

            $scope.addGoodsExist = null;
            $scope.$watch('addGoodsExist', function(){
                angular.element('#addGoodsExist').prop('checked', false);
            });

            $rootScope.$broadcast('categoryBroadcastToList', {
                categoryToList: null
            });
            $scope.addGoodsCategory = null;
            $scope.selectCat = null;

            $rootScope.$broadcast('consumerBroadcastToList', {
                consumerToList: null
            });
            $scope.adminConsumerSelect = null;
            $scope.selectConsumer = null;

            $rootScope.$broadcast('colorBroadcastToList', {
                colorToList: null
            });
            $scope.adminColorSelect = null;
            $scope.selectColor = null;

            $rootScope.$broadcast('typeBroadcastToList', {
                typeToList: null
            });
            $scope.adminTypeSelect = null;
            $scope.selectType = null;

            $rootScope.$broadcast('sizeBroadcastToList', {
                sizeToList: null
            });
            $scope.sizeSelect = null;
            $scope.selectSize = null;

        });

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
                    $scope.savedIdCategoryImg = response.data;
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
            $scope.savedIdCategoryImg = null;
        };

        $scope.saveCategory = function(name, parentId){
            var category = {
                "name": name,
                "parentId": parentId,
                "image": $scope.savedIdCategoryImg
            };
            saveCategoryFactory.save(category);
            $scope.categories = allChildCategoryFactory.query();
            $scope.name_category = '';
            $scope.savedIdCategoryImg = null;
            $scope.isCategoryAdd = false;
            $scope.newGood = true;
            $scope.img_category = null;
        };

    });
