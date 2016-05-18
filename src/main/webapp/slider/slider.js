angular.module('monolitApp.slider', ['ui.bootstrap']);
monolit.controller('carouselCtrl', function($scope, getAllSlidesFactory) {
    $scope.myInterval = 3000;
    $scope.slides = getAllSlidesFactory.query();
});