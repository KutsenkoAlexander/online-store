angular.module('monolitApp.admin.goods', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.goods', {
                url: "/goods",
                views: {
                    'admin': {
                        templateUrl: "admin/goods/goods.html",
                        controller: 'adminGoodsCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление товарами'
                }
            })
    })

    .factory('getAllProductsFactory', function ($resource) {
        return $resource('/product/all', {}, {'query': {method: 'GET', isArray: false}});
    })

    .factory('saveProductFactory', function ($resource) {
        return $resource('/product/save', {}, {'save': {method: 'POST'}});
    })

    .factory('allChildCategoryFactory', function ($resource) {
        return $resource('/catalog/category/child', {}, {});
    })

    .factory('allParentCategoryFactory', function ($resource) {
        return $resource('/catalog/category/parent', {}, {});
    })

    .controller('adminGoodsCtrl', function ($rootScope,
                                            $scope,
                                            $location,
                                            getAllProductsFactory,
                                            pageCacheFactory,
                                            saveProductFactory,
                                            allChildCategoryFactory,
                                            Upload,
                                            $timeout) {
        $scope.editableGoodsState = false;
        $scope.newGood = false;
        $scope.fastEdit = false;
        $scope.editItem = null;
        $scope.currentUrl = $rootScope.currentPath;
        $scope.editableProducts = getAllProductsFactory.query();

        $scope.$on("categoriesBroadcast", function (event, args) {
            $scope.categories = args.categories;
        });

        $scope.$on('searched', function (event, data) {
            $scope.fastEdit = data.fastEdit;
            data.productSearched.$promise.then(function (product) {
                $scope.editableProduct = product;
            });
        });

        $scope.addGood = function () {
            $scope.newGood = true;
            $scope.editItem = null;
            $scope.editableProduct = false;
            $scope.editableGoodsState = false;
            $scope.categories = allChildCategoryFactory.query();
        };

        $scope.editGood = function () {
            $scope.editableGoods = null;
            $scope.editItem = null;
            $scope.editSingleItem = null;
            $scope.editableGoodsState = true;
            $scope.fastEdit = false;
            $scope.newGood = false;
            $scope.editableProduct = false;
            $scope.categories = allChildCategoryFactory.query();
        };

        $scope.saveGood = function (description, exist, img, price, code, category,  adminColorSelect, adminConsumerSelect, sizeSelect, adminTypeSelect, name_product) {
            var productExist;
            console.log(exist);
            switch (exist) {
                case true:
                    productExist = 1;
                    break;
                case false:
                    productExist = 0;
                    break;
            }
            var product = {
                "description": description,
                "exist": productExist,
                "image": $scope.savedIdCategoryImg,
                "price": price.number,
                "productCode": code,
                "sprCategory": category,
                "sprColor": adminColorSelect,
                "sprConsumer": adminConsumerSelect,
                "sprSize": sizeSelect,
                "sprType": adminTypeSelect,
                "title": name_product
            };
            console.log(product);
            saveProductFactory.save(product);
            $scope.newGood = false;
            $scope.editableProducts = getAllProductsFactory.query();
        };

        $scope.cancelEditGood = function () {
            $scope.editableGoodsState = false;
            $scope.newGood = false;
        };

        $scope.fastEditGood = function (id, price, exist) {
            $scope.editItem = id;
            $scope.price = price;
            switch (exist) {
                case 1 :
                    $scope.exist = true;
                    break;
                case 0:
                    $scope.exist = false;
                    break;
            }
            $scope.fastEdit = true;
        };

        $scope.fastEditSingleGood = function (id, price, exist) {
            if (!$scope.fastEdit) {
                $scope.editSingleItem = id;
                $scope.editableProductPrice = price;
                switch (exist) {
                    case 1 :
                        $scope.editableProductExist = true;
                        break;
                    case 0:
                        $scope.editableProductExist = false;
                        break;
                }
                $scope.fastEdit = true;
            }
        };

        $scope.cancelFastEditGoods = function () {
            $scope.editSingleItem = null;
            $scope.editItem = null;
            $scope.fastEdit = false;
        };

        $scope.saveFastEditGood = function (price, exist, product) {
            var productExist;
            switch (exist) {
                case true:
                    productExist = 1;
                    break;
                case false:
                    productExist = 0;
                    break;
            }
            product.price = price;
            product.exist = productExist;
            var result = saveProductFactory.save(product);
            $scope.editItem = null;
            $scope.fastEdit = false;

        };

        $scope.showAllProducts = function () {
            $scope.editItem = null;
            $scope.editableProduct = false;
            $scope.editableGoodsState = false;
            $scope.editableProducts = getAllProductsFactory.query();
        };

        $scope.uploadImageProduct = function(file, errFiles) {
            $scope.img_product = file;
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


    });
