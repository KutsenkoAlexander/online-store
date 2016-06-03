var monolit = angular.module('monolitApp', [
    'ui.router',
    'ngResource',
    'angucomplete',
    'angular.filter',
    'ngSanitize',
    'textAngular',
    'ngFileUpload',
    'monolitApp.slider',
    'monolitApp.home',
    'monolitApp.about',
    'monolitApp.career',
    'monolitApp.customers',
    'monolitApp.delivery',
    'monolitApp.contacts',
    'monolitApp.sale',
    'monolitApp.category',
    'monolitApp.productList',
    'monolitApp.product',
    'monolitApp.breadcrumbs',
    'monolitApp.catalog',
    'monolitApp.sort',
    'monolitApp.pagination',
    'monolitApp.navigation',
    'monolitApp.admin',
    'monolitApp.admin.page',
    'monolitApp.admin.goods',
    'monolitApp.admin.slider',
    'monolitApp.admin.categories',
    'monolitApp.admin.consumer',
    'monolitApp.admin.color',
    'monolitApp.admin.type',
    'monolitApp.admin.goods.code',
    'monolitApp.login',
    'monolitApp.admin.size'
]);

monolit.config(function ($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {
    $locationProvider.html5Mode(true).hashPrefix('!');
    $urlRouterProvider.otherwise('/');
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

monolit.run(function($rootScope,$location){
    if($location.path().length > 1 && $location.path().substr($location.path().length - 1) === "/") {
        $location.path($location.path().slice(0, -1))
    }

    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
        $rootScope.pageTitle = toState.data.pageTitle;
        $rootScope.currentPath = toState.url;
        $rootScope.state = toState.name;
    });
});

monolit.factory('pageFactory', function($resource){
    return $resource('/page/:url', {url: '@url'},{'query': {method: 'GET', isArray:false}});
});

monolit.factory('pageCacheFactory', function ($cacheFactory) {
    return $cacheFactory('pageCache', {});
});

monolit.controller('mainCtrl', function($locale){
    $locale.NUMBER_FORMATS.GROUP_SEP = ' ';
});

monolit.controller('currentPage', function(){});

/*
 *  DERECTIVES
 */
monolit.directive('subNavigation', function(){
    return {
        link: function(scope, element, attr, ctrl){
            $(window).scroll(function () {
                var top = $(document).scrollTop();
                //navigation main
                if (top < 65) {
                    $(".img_nav").css({
                        'opacity': '0'
                    });
                    $("nav").css({
                        'transform': 'translate(-45px,0)',
                        '-webkit-transform': 'translate(-45px,0)',
                        '-o-transform': 'translate(-45px,0)',
                        '-moz-transform': 'translate(-45px,0)'
                    });
                } else {
                    $(".img_nav").css({
                        "opacity": "1",
                        '-webkit-transition': 'opacity 0.5s ease 0.0s',
                        '-moz-transition': 'opacity 0.5s ease 0.0s',
                        '-o-transition': 'opacity 0.5s ease 0.0s',
                        'transition': 'opacity 0.5s ease 0.0s'
                    });
                    $("nav").css({
                        'transform': 'translate(0,0)',
                        '-webkit-transform': 'translate(0,0)',
                        '-o-transform': 'translate(0,0)',
                        '-moz-transform': 'translate(0,0)'
                    });
                }

                //sub_bar
                if (top < 103) {
                    $(".sub_navigate_bar").css({
                        'transform': 'translate(0,-140px)',
                        '-webkit-transform': 'translate(0,-140px)',
                        '-o-transform': 'translate(0,-140px)',
                        '-moz-transform': 'translate(0,-140px)'
                    });
                } else {
                    $(".sub_navigate_bar").css({
                        'position': 'fixed',
                        'background-color':'#ffffff',
                        'box-shadow': '0px 2px 40px rgba(0, 0, 0, 0.3)',
                        'z-index': '95',
                        'width': '100%',
                        'min-height':'40px',
                        'height':'auto',
                        'transform': 'translate(0,40px)',
                        '-webkit-transform': 'translate(0,40px)',
                        '-o-transform': 'translate(0,40px)',
                        '-moz-transform': 'translate(0,40px)'
                    });
                }
            });
        }
    }
});

monolit.directive('subCategory', function(){
    return {
        link: function(scope, element, attr, ctrl){
            $(".child_category")
                .mouseover(function() {
                    $(this).find("h2").css({
                        'opacity' : '0',
                        'top' : '-200px',
                        '-webkit-transition' : 'opacity 0.5s ease 0.0s',
                        '-moz-transition' : 'opacity 0.5s ease 0.0s',
                        '-o-transition' : 'opacity 0.5s ease 0.0s',
                        'transition' : 'opacity 0.5s ease 0.0s'
                    });
                    $(this).find("img").css({
                        'width': '91%',
                        '-webkit-transition' : 'width 0.4s ease 0.0s',
                        '-moz-transition' : 'width 0.4s ease 0.0s',
                        '-o-transition' : 'width 0.4s ease 0.0s',
                        'transition' : 'width 0.4s ease 0.0s'
                    });

                })
                .mouseout(function() {
                    $(this).find("h2").css({
                        'opacity' : '1',
                        'font-size': '27px',
                        'font-weight': 'bold',
                        'color': '#212121',
                        'text-decoration': 'none',
                        'position': 'relative',
                        'top': '-200px',
                        'padding': '30px 0px'
                    });
                    $(this).find("img").css({
                        'width': '88%',
                        '-webkit-transition' : 'width 0.4s ease 0.0s',
                        '-moz-transition' : 'width 0.4s ease 0.0s',
                        '-o-transition' : 'width 0.4s ease 0.0s',
                        'transition' : 'width 0.4s ease 0.0s'
                    });
                });
        }
    }
});
