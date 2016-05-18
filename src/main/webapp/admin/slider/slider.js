angular.module('monolitApp.admin.slider', ['ui.router', 'ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('admin.slider', {
                url: "/slider",
                views: {
                    'admin': {
                        templateUrl: "admin/slider/slider.html",
                         controller: 'adminSliderCtrl'
                    }
                },
                data: {
                    pageTitle: 'Управление слайдером'
                }
            });
    })

    .factory('getAllSlidesFactory', function ($resource) {
        return $resource('/slide/all', {}, {'query': {method: 'GET', isArray: true}});
    })

    .factory('saveSlideFactory', function ($resource) {
        return $resource('/admin/slide/save', {}, {'save': {method: 'POST'}});
    })

    .controller('adminSliderCtrl', function($rootScope, $scope, getAllSlidesFactory){
        $scope.slides = getAllSlidesFactory.query();
    })

    .directive('slideImg', function(){
        return {
            link: function(scope, element, attr, ctrl){
                $(".edit_slider")
                    .mouseover(function() {
                        $(this).find("button").css({
                            'opacity' : '1',
                            '-webkit-transition' : 'opacity 0.5s ease 0.0s',
                            '-moz-transition' : 'opacity 0.5s ease 0.0s',
                            '-o-transition' : 'opacity 0.5s ease 0.0s',
                            'transition' : 'opacity 0.5s ease 0.0s'
                        });
                        $(this).find("img").css({
                            'opacity' : '0.5',
                            '-webkit-transition' : 'opacity 0.5s ease 0.0s',
                            '-moz-transition' : 'opacity 0.5s ease 0.0s',
                            '-o-transition' : 'opacity 0.5s ease 0.0s',
                            'transition' : 'opacity 0.5s ease 0.0s'
                        });
                    })
                    .mouseout(function() {
                        $(this).find("button").css({
                            'opacity' : '0'
                        });
                        $(this).find("img").css({
                            'opacity' : '1'
                        });
                    });
            }
        }
    });