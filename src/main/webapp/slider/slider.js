angular.module('monolitApp.slider', ['ui.bootstrap'])
    .controller('carouselCtrl', function($scope, getAllSlidesFactory) {
        $scope.myInterval = 3000;
        $scope.slides = getAllSlidesFactory.query();
    });