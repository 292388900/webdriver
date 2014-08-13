app.controller('UploadController', function($scope, $http) {
    $scope.doc = {};


  $scope.upload = function(){
      $http.post('doc/upload', $scope.doc).success(function(result) {
          if(result.success == true) {

          }else{
              alert("error");
          }
      });
  }

});