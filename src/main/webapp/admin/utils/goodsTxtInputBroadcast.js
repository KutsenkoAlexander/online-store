angular.module('monolitApp.admin.goods.code', [])
    
    .controller('goodsTxtInputCtrl', function($scope, $rootScope){

        $scope.$on("goodsTxtInputBroadcast", function (event, args) {
            $scope.addGoodsCode = args.goodsCode;
            $scope.name_product = args.nameProduct;
            $scope.description = args.descriptionProduct;
            $scope.priceUah = args.priceUahProduct;
            $scope.priceCent = args.priceCentProduct;
            $scope.addGoodsExist = args.existProduct;
        });

    });
