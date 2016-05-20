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
        return $resource('/rest/product/save', {}, {'save': {method: 'POST'}});
    })

    .factory('deleteProductFactory', function ($resource) {
        return $resource('/rest/product/delete/:id', {id: '@id'},{'delete': { method: 'DELETE' }});
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
                                            productByIdFactory,
                                            deleteProductFactory) {
        $scope.editableGoodsState = false;
        $scope.newGood = false;
        $scope.fastEdit = false;
        $scope.editItem = null;
        $scope.currentUrl = $rootScope.currentPath;

        var search = $location.search();
        var page = search.page || 0;
        var size = search.size || 15;
        var sort = search.sort || 'type,desc';
        var existProduct;

        $scope.setPageAndSizeAdmin = function (page) {
            $http({method: 'GET', url: '/product/all?page=' + page + '&size=' + size})
                .success(function (data) {
                    $scope.editableProducts = data.content;
                    $scope.page = data.page;
                    $scope.sort = sort;

                    angular.forEach(data.links, function (value) {
                        if (value.rel === 'next') {
                            $scope.nextPageLink = value.href;
                        }
                        if (value.rel === 'prev') {
                            $scope.prevPageLink = value.href;
                        }
                    });
                })
                .error(function (error) {
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
            $scope.addGoodsCategory = args.selectCat;
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

        var setEmptyAllFieldsAfterCloseOrSaveProduct = function () {
            $scope.savedIdCategoryImg = null;
            $scope.img_product = null;
            $scope.savedIdProductImg = null;

            $rootScope.$broadcast('goodsTxtInputBroadcast', {
                goodsCode: null
            });
            $scope.$watch('addGoodsCode', function () {
                angular.element('#addGoodsCode').val('');
            });

            $rootScope.$broadcast('goodsTxtInputBroadcast', {
                nameProduct: null
            });
            $scope.$watch('name_product', function () {
                angular.element('#name_product').val('');
            });

            $rootScope.$broadcast('goodsTxtInputBroadcast', {
                descriptionProduct: null
            });
            $scope.$watch('description', function () {
                angular.element('#description').val('');
            });

            $rootScope.$broadcast('goodsTxtInputBroadcast', {
                priceUahProduct: null
            });
            $scope.$watch('priceUah', function () {
                angular.element('#priceUah').val('');
            });

            $rootScope.$broadcast('goodsTxtInputBroadcast', {
                priceCentProduct: null
            });
            $scope.$watch('priceCent', function () {
                angular.element('#priceCent').val('');
            });

            $rootScope.$broadcast('goodsTxtInputBroadcast', {
                existProduct: null
            });
            $scope.$watch('addGoodsExist', function () {
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

            $scope.editItem = null;
            $scope.newGood = false;
            $scope.editableProduct = false;
            $scope.editableGoodsState = false;
            $scope.isCategoryAdd = false;

            $scope.editableGoods = null;
            $scope.editSingleItem = null;
            $scope.fastEdit = false;
        };

        $scope.addGood = function () {
            $scope.editItem = null;
            $scope.newGood = true;
            $scope.editableProduct = false;
            $scope.editableGoodsState = false;
            $scope.isCategoryAdd = false;

            $scope.editableGoods = null;
            $scope.editSingleItem = null;
            $scope.fastEdit = false;

            //code
            $scope.$on('codeBroadcast', function (event, args) {
                $scope.goodsCode = args.goodsCode;
            });

            //title
            $scope.$on('titleBroadcast', function (event, args) {
                $scope.titleProduct = args.titleBroadcast;
            });

            //description
            $scope.$on('descriptionBroadcast', function (event, args) {
                $scope.descriptionProduct = args.descriptionBroadcast;
            });

            //price
            $scope.$on('priceUahBroadcast', function (event, args) {
                $scope.uah = args.priceUahBroadcast;
            });
            $scope.$on('priceCentBroadcast', function (event, args) {
                $scope.cent = args.priceCentBroadcast;
            });

            //exist
            $scope.$on('productExistBroadcast', function (event, args) {
                $scope.productExist = args.productExistBroadcast;
            });

            //category
            $rootScope.$broadcast('categoryBroadcastToList', {
                categoryToList: ''
            });

            //consumer
            $rootScope.$broadcast('consumerBroadcastToList', {
                consumerToList: ''
            });

            //color
            $rootScope.$broadcast('colorBroadcastToList', {
                colorToList: ''
            });

            //type
            $rootScope.$broadcast('typeBroadcastToList', {
                typeToList: ''
            });

            //size
            $rootScope.$broadcast('sizeBroadcastToList', {
                sizeToList: ''
            });

            $scope.categories = allChildCategoryFactory.query();
        };

        $scope.editGood = function (id) {
            $scope.editItem = null;
            $scope.newGood = false;
            $scope.editableProduct = false;
            $scope.editableGoodsState = true;
            $scope.isCategoryAdd = false;

            $scope.editableGoods = null;
            $scope.editSingleItem = null;
            $scope.fastEdit = false;
            $scope.btnSave = true;

            var editProduct = productByIdFactory.query({id: id});

            editProduct.$promise.then(function (data) {

                var priceUah;
                var priceCent;
                var addGoodsExist;

                $scope.idProductEdit = data.idProduct;

                //image
                $scope.savedIdProductImg = data.image;

                //code
                $scope.$on('codeBroadcast', function (event, args) {
                    $scope.goodsCode = args.goodsCode;
                });
                $scope.$watch('addGoodsCode', function () {
                    angular.element('#addGoodsCode').val(data.productCode);
                });

                //title
                $scope.$on('titleBroadcast', function (event, args) {
                    $scope.titleProduct = args.titleBroadcast;
                });
                $scope.$watch('name_product', function () {
                    angular.element('#name_product').val(data.title);
                });

                //description
                $scope.$on('descriptionBroadcast', function (event, args) {
                    $scope.descriptionProduct = args.descriptionBroadcast;
                });
                $scope.$watch('description', function () {
                    angular.element('#description').val(data.description);
                });

                //price
                var price = data.price.toFixed(2);
                var index = price.indexOf(".");
                if (index > -1) {
                    priceUah = parseInt(price.substring(0, index));
                    priceCent = parseInt(price.substring(index + 1));
                    $scope.$on('priceUahBroadcast', function (event, args) {
                        $scope.uah = args.priceUahBroadcast;
                    });
                    $scope.$on('priceCentBroadcast', function (event, args) {
                        $scope.cent = args.priceCentBroadcast;
                    });
                    $scope.$watch('description', function () {
                        angular.element('#priceUah').val(price.substring(0, index));
                        angular.element('#priceCent').val(parseInt(price.substring(index + 1)));
                    });
                } else {
                    priceUah = parseInt(data.price);
                    priceCent = 00;
                    $scope.cent = 00;
                    $scope.$on('priceUahBroadcast', function (event, args) {
                        $scope.uah = args.priceUahBroadcast;
                    });
                    $scope.$watch('description', function () {
                        angular.element('#priceUah').val(parseInt(data.price));
                        angular.element('#priceCent').val('00');
                    });
                }

                //exist
                switch (data.exist) {
                    case 1:
                        addGoodsExist = true;
                        $scope.$on('productExistBroadcast', function (event, args) {
                            $scope.productExist = args.productExistBroadcast;
                        });
                        $scope.$watch('addGoodsExist', function () {
                            angular.element('#addGoodsExist').prop('checked', true);
                        });
                        break;
                    case 0:
                        addGoodsExist = false;
                        $scope.$on('productExistBroadcast', function (event, args) {
                            $scope.productExist = args.productExistBroadcast;
                        });
                        $scope.$watch('addGoodsExist', function () {
                            angular.element('#addGoodsExist').prop('checked', false);
                        });
                        break;
                }

                $rootScope.$broadcast('categoryBroadcastToList', {
                    categoryToList: data.sprCategory
                });
                $scope.addGoodsCategory = data.sprCategory;
                $scope.selectCat = data.sprCategory;

                $rootScope.$broadcast('consumerBroadcastToList', {
                    consumerToList: data.sprConsumer
                });
                $scope.adminConsumerSelect = data.sprConsumer;
                $scope.selectConsumer = data.sprConsumer;

                $rootScope.$broadcast('colorBroadcastToList', {
                    colorToList: data.sprColor
                });
                $scope.adminColorSelect = data.sprColor;
                $scope.selectColor = data.sprColor;

                $rootScope.$broadcast('typeBroadcastToList', {
                    typeToList: data.sprType
                });
                $scope.adminTypeSelect = data.sprType;
                $scope.selectType = data.sprType;

                $rootScope.$broadcast('sizeBroadcastToList', {
                    sizeToList: data.sprSize
                });
                $scope.sizeSelect = data.sprSize;
                $scope.selectSize = data.sprSize;

                $rootScope.$broadcast('goodsTxtInputBroadcast', {
                    goodsCode: data.productCode,
                    nameProduct: data.title,
                    descriptionProduct: data.description,
                    priceUahProduct: priceUah,
                    priceCentProduct: priceCent,
                    existProduct: addGoodsExist
                });
            });

            $scope.categories = allChildCategoryFactory.query();
        };

        $scope.saveGood = function (description, exist, img, priceUah, priceCent, code, name_product, page_number) {
            var productExist;
            switch (exist) {
                case true:
                    productExist = 1;
                    break;
                case false:
                    productExist = 0;
                    break;
            }
            if (angular.isUndefined(priceCent) || priceCent === null) {
                priceCent = 00;
            }
            var price = priceUah + "." + priceCent;
            var product = {
                "idProduct": $scope.idProductEdit,
                "description": $scope.descriptionProduct,
                "exist": productExist,
                "image": img,
                "price": price,
                "productCode": code,
                "title": name_product,
                "sprCategory": $scope.addGoodsCategory,
                "sprColor": $scope.selectColor,
                "sprConsumer": $scope.selectConsumer,
                "sprSize": $scope.selectSize,
                "sprType": $scope.selectType
            };
            saveProductFactory.save(product,
                function (resp, headers) {
                    //success callback
                    setEmptyAllFieldsAfterCloseOrSaveProduct();
                    $scope.setPageAndSizeAdmin(page_number);
                },
                function (err) {
                    // error callback
                    alert(err.data.message);
                }
            );
        };

        $scope.cancelEditGood = function () {
            setEmptyAllFieldsAfterCloseOrSaveProduct();
        };

        $scope.fastEditSingleGood = function (id, price, exist) {
            if (!$scope.fastEdit) {
                $scope.fastEdit = true;
                $scope.editSingleItem = id;
                //price
                $rootScope.$broadcast('priceProductBroadcast',{
                    priceProduct: price
                });
                $scope.$on('fastEditPriceProductBroadcast',function(event, args){
                    $scope.editableProductPrice = args.fastEditPriceProductBroadcast;
                });
                //exist
                $rootScope.$broadcast('existProductBroadcast',{
                    existProduct: exist
                });
                $scope.$on('fastEditExistProductBroadcast',function(event, args){
                    $scope.editableProductExist = args.fastEditExistProductBroadcast;
                });
            }
        };

        $scope.fastEditGood = function (id, price, exist) {
            $scope.fastEdit = true;
            $scope.editItem = id;
            //price
            $rootScope.$broadcast('priceProductBroadcast',{
                priceProduct: price
            });
            $scope.$on('fastEditPriceProductBroadcast',function(event, args){
                $scope.price = args.fastEditPriceProductBroadcast;
            });
            //exist
            $rootScope.$broadcast('existProductBroadcast',{
                existProduct: exist
            });
            $scope.$on('fastEditExistProductBroadcast',function(event, args){
                $scope.exist = args.fastEditExistProductBroadcast;
            });
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
            console.log(product);
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

        $scope.uploadImageProduct = function (file, errFiles, idImage) {
            $scope.img_product = file;
            $scope.errFile = errFiles && errFiles[0];
            if (file) {
                file.upload = Upload.upload({
                    url: '/rest/image/save',
                    data: {file: file, idImage: idImage}
                });

                file.upload.then(function (response) {
                    $scope.savedIdProductImg = response.data;
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

        $scope.deleteProduct = function(id, page, nameProduct){
            var result = confirm('Удалить \"'+nameProduct+'\" ?');
            if(result){
                deleteProductFactory.delete({id:id},
                    function (resp, headers) {
                        //success callback
                        $scope.setPageAndSizeAdmin(page);
                        $scope.editItem = null;
                        $scope.editableProduct = false;
                        $scope.editableGoodsState = false;
                    },
                    function (err) {
                        // error callback
                        confirm("Ошибка удаления товара!");
                    }
                );
            }
        }

    })

    .controller('fastEditProductCtrl', function($rootScope, $scope){
        //price
        $scope.$on('priceProductBroadcast',function(event, args){
            $scope.price = args.priceProduct;
        });
        $scope.$watch('price', function(){
            $rootScope.$broadcast('fastEditPriceProductBroadcast',{
                fastEditPriceProductBroadcast: $scope.price
            });
        });
        //exist
        $scope.$on('existProductBroadcast',function(event, args){
            switch (args.existProduct) {
                case 1 :
                    $scope.exist = true;
                    break;
                case 0:
                    $scope.exist = false;
                    break;
            }
        });
        $scope.$watch('exist', function(){
            $rootScope.$broadcast('fastEditExistProductBroadcast',{
                fastEditExistProductBroadcast: $scope.exist
            });
        });
    })

    .controller('fastEditSingleProductCtrl', function($rootScope, $scope){
        //price
        $scope.$on('priceProductBroadcast',function(event, args){
            $scope.editableProductPrice = args.priceProduct;
        });
        $scope.$watch('editableProductPrice', function(){
            $rootScope.$broadcast('fastEditPriceProductBroadcast',{
                fastEditPriceProductBroadcast: $scope.editableProductPrice
            });
        });
        //exist
        $scope.$on('existProductBroadcast',function(event, args){
            switch (args.existProduct) {
                case 1 :
                    $scope.editableProductExist = true;
                    break;
                case 0:
                    $scope.editableProductExist = false;
                    break;
            }
        });
        $scope.$watch('editableProductExist', function(){
            $rootScope.$broadcast('fastEditExistProductBroadcast',{
                fastEditExistProductBroadcast: $scope.editableProductExist
            });
        });
    });
