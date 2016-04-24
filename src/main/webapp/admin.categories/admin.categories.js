angular.module('monolitApp.admin.categories', ['ngResource'])

    .factory('saveCategoryFactory', function($resource){
        return $resource('/catalog/category/save', {},{'save': {method:'POST'}});
    })

    .controller('adminCategoryCtrl', function($scope, $http,
                                              allParentCategoryFactory,
                                              allChildCategoryFactory,
                                              saveCategoryFactory,
                                              Upload, $timeout){

        $scope.files = [];
        $scope.blobFiles = [];

        $scope.addCategory = function(){
            $scope.isCategoryAdd = true;
            $scope.newGood = false;
            $scope.parenCategories = allParentCategoryFactory.query();
        };

        $scope.uploadFiles = function(file, errFiles) {
            $scope.f = file;
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

        $scope.cancelAddCategory = function(){
            $scope.isCategoryAdd = false;
            $scope.newGood = true;
        };

        $scope.saveCategory = function(name, parentId){
            $scope.isCategoryAdd = false;
            $scope.newGood = true;
            var category = {
                "name": name,
                "parentId": parentId,
                "image": $scope.savedIdCategoryImg
            };
            saveCategoryFactory.save(category);
            $scope.categories = allChildCategoryFactory.query();
        }
    });
