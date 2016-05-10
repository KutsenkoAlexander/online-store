angular.module('monolitApp.admin.goods.add', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.goods.add', {
                url: "/add",
                views: {
                    'admin': {
                        templateUrl: "admin/goods/goods.add.html",
                        controller: 'adminGoodsAddCtrl'
                    }
                },
                data: {
                    pageTitle: 'Добавление товара'
                }
            })
    })