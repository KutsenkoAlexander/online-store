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

    .controller('adminSliderCtrl', function($rootScope,
                                            $scope,
                                            getAllSlidesFactory,
                                            Upload,
                                            $timeout){
        $scope.slides = getAllSlidesFactory.query();

        $scope.uploadSlide = function(file, errFiles, idImage) {
            $scope.img_slider = file;
            $scope.errFile = errFiles && errFiles[0];
            if (file) {
                file.upload = Upload.upload({
                    url: '/image/save',
                    data: {file: file, idImage: idImage}
                });

                file.upload.then(function (response) {
                    $scope.savedSlide = response.data;
                    $timeout(function () {
                        file.result = response.data;
                    });
                    $scope.slides = getAllSlidesFactory.query();
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