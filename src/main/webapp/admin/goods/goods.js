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
                                            $timeout,
                                            $http,
                                            productByIdFactory) {
        $scope.editableGoodsState = false;
        $scope.newGood = false;
        $scope.fastEdit = false;
        $scope.editItem = null;
        $scope.currentUrl = $rootScope.currentPath;

        var search = $location.search();
        var page = search.page||0;
        var size = search.size||5;
        var sort = search.sort||'type,desc';

        $scope.setPageAndSizeAdmin = function(page){
            $http({method: 'GET', url: '/product/all?page=' + page + '&size=' + size})
                .success(function(data) {
                    $scope.editableProducts = data.content;
                    $scope.page = data.page;
                    $scope.sort = sort;

                    angular.forEach(data.links, function(value) {
                        if(value.rel === 'next') {
                            $scope.nextPageLink = value.href;
                        }

                        if(value.rel === 'prev') {
                            $scope.prevPageLink = value.href;
                        }
                    });
                })
                .error(function(error) {
                    $scope.widgetsError = error;
                });
        };

        $scope.setPageAndSizeAdmin(0);

        $scope.$on('searched', function (event, data) {
            $scope.fastEdit = data.fastEdit;
            data.productSearched.$promise.then(function (product) {
                $scope.editableProduct = product;
            });
        });

        $scope.$on("catBroadcast", function (event, args) {
            $scope.selectCat = args.selectCat;

        });

        $scope.$on("selectColor", function (event, args) {
            $scope.selectColor = args.selectColor;
        });

        $scope.$on("selectConsumer", function (event, args) {
            $scope.selectConsumer = args.selectConsumer;
        });

        $scope.$on("selectSize", function (event, args) {
            $scope.selectSize = args.selectSize;
        });

        $scope.$on("selectType", function (event, args) {
            $scope.selectType = args.selectType;
        });

        $scope.addGood = function () {
            $scope.newGood = true;
            $scope.editItem = null;
            $scope.editableProduct = false;
            $scope.editableGoodsState = false;
            $scope.savedIdCategoryImg = null;

            $scope.addGoodsCategory = null;

            $scope.addGoodsCode = '';
            $scope.$watch('addGoodsCode', function(){
                angular.element('#addGoodsCode').val('');
            });

            $scope.name_product = '';
            $scope.description = '';
            $scope.priceUah = '';
            $scope.priceCent = '';
            $scope.addGoodsExist = '';
            $scope.adminConsumerSelect = null;
            $scope.adminColorSelect = null;
            $scope.adminTypeSelect = null;
            $scope.sizeSelect = null;
            $scope.isCategoryAdd = false;
            $scope.categories = allChildCategoryFactory.query();
        };

        $scope.editGood = function (id) {
            $scope.editableGoods = null;
            $scope.editItem = null;
            $scope.editSingleItem = null;
            $scope.editableGoodsState = true;
            $scope.fastEdit = false;
            $scope.newGood = false;
            $scope.editableProduct = false;
            $scope.isCategoryAdd = false;

            var editProduct = productByIdFactory.query({id:id});
            editProduct.$promise.then(function(data){
                //console.log(data);
                $scope.idProductEdit = data.idProduct;
                $scope.savedIdCategoryImg = data.image;

                $scope.addGoodsCode = data.productCode;
                $scope.$watch('addGoodsCode', function(){
                    angular.element('#addGoodsCode').val(data.productCode);
                });

                $scope.name_product = data.title;
                $scope.description = data.description;
                var price = data.price.toFixed(2);
                var index = price.indexOf(".");
                if(index > -1){
                    $scope.priceUah = price.substring(0, index);
                    $scope.priceCent = parseInt(price.substring(index+1));
                } else {
                    $scope.priceUah = parseInt(data.price);
                    $scope.priceCent = 00;
                }
                switch(data.exist){
                    case 1:
                        $scope.addGoodsExist = true;
                        break;
                    case 0:
                        $scope.addGoodsExist = false;
                        break;
                }

                //////////////////////////////////////////

                $scope.addGoodsCategory = data.sprCategory;
                $scope.selectCat = data.sprCategory;

                angular.element('#addGoodsCategory').val(data.sprCategory);
                console.log(data.sprCategory);

                /////////////////////////////////////////

                $scope.adminConsumerSelect = data.sprConsumer;
                $scope.selectConsumer = data.sprConsumer;

                $scope.adminColorSelect = data.sprColor;
                $scope.selectColor = data.sprColor;

                $scope.adminTypeSelect = data.sprType;
                $scope.selectType = data.sprType;

                $scope.sizeSelect = data.sprSize;
                $scope.selectSize = data.sprSize;
            });

            $scope.categories = allChildCategoryFactory.query();
        };

        $scope.saveGood = function (description,
                                    exist,
                                    img,
                                    priceUah,
                                    priceCent,
                                    code,
                                    name_product) {
            var productExist;
            switch (exist) {
                case true:
                    productExist = 1;
                    break;
                case false:
                    productExist = 0;
                    break;
            }
            if(angular.isUndefined(priceCent)){
                priceCent = 00;
            }
            var price = priceUah+"."+priceCent;
            var product = {
                "idProduct": $scope.idProductEdit,
                "description": description,
                "exist": productExist,
                "image": $scope.savedIdCategoryImg,
                "price": price,
                "productCode": code,
                "title": name_product,
                "sprCategory": $scope.selectCat,
                "sprColor": $scope.selectColor,
                "sprConsumer": $scope.selectConsumer,
                "sprSize": $scope.selectSize,
                "sprType": $scope.selectType
            };
            saveProductFactory.save(product);
            $scope.editableGoodsState = false;
            $scope.newGood = false;
            $scope.savedIdCategoryImg = null;
            $scope.addGoodsCategory = null;
            $scope.addGoodsCode = '';
            $scope.name_product = '';
            $scope.description = '';
            $scope.priceUah = '';
            $scope.priceCent = '';
            $scope.addGoodsExist = '';
            $scope.adminConsumerSelect = null;
            $scope.adminColorSelect = null;
            $scope.adminTypeSelect = null;
            $scope.sizeSelect = null;
            $scope.setPageAndSizeAdmin(0);
            $scope.setPageAndSizeAdmin(0);
        };

        $scope.cancelEditGood = function () {
            $scope.savedIdCategoryImg = null;
            $scope.addGoodsCategory = null;
            $scope.addGoodsCode = '';
            $scope.name_product = '';
            $scope.description = '';
            $scope.priceUah = '';
            $scope.priceCent = '';
            $scope.addGoodsExist = '';
            $scope.adminConsumerSelect = null;
            $scope.adminColorSelect = null;
            $scope.adminTypeSelect = null;
            $scope.sizeSelect = null;

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
            $scope.setPageAndSizeAdmin(0);
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
