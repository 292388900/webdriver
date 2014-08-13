app.controller('WebDocController', function($scope, $http) {

    $scope.docs = [];


    $scope.list = function() {
        $http.get('doc/list').success(function(data){
            $scope.docs = data;
        });
    };

    $scope.remove = function(i){
        var id = $scope.docs[i].id;
        $http.post('doc/delete/'+id).success(function (result) {
            if(result.success == true) {
                $scope.docs.splice(i, 1);
            }else{
                alert("error");
            }
        });
    };

    $scope.doc = {};

    $scope.upload = function(){
        $http.post('doc/upload', $scope.doc).success(function(result) {
            if(result.success == true) {

            }else{
                alert("error");
            }
        });
    };


    //init
    $scope.list();

});