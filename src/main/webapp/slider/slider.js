angular.module('monolitApp.slider', ['ui.bootstrap']);
monolit.controller('carouselCtrl', function($scope) {
    $scope.myInterval = 3000;
    $scope.slides = [
        {
            image: '../static/img/slider/1.jpg'
        },
        {
            image: '../static/img/slider/2.jpg'
        },
        {
            image: '../static/img/slider/3.jpg'
        },
        {
            image: '../static/img/slider/4.jpg'
        },
        {
            image: '../static/img/slider/5.jpg'
        },
        {
            image: '../static/img/slider/6.jpg'
        },
        {
            image: '../static/img/slider/7.jpg'
        }
    ];
});