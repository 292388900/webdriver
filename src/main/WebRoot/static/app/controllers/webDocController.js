app.controller('WebDocController', function ($scope, $http, FileUploader) {


    $scope.docs = [];
    $scope.uploadDoc={};

    $scope.list = function () {
        $http.get('doc/list').success(function (data) {
            $scope.docs = data;
        });
    };

    $scope.remove = function (i) {
        var id = $scope.docs[i].id;
        $http.post('doc/delete/' + id).success(function (result) {
            if (result.success == true) {
                $scope.docs.splice(i, 1);
            } else {
                alert("error");
            }
        });
    };

    $scope.upload = function () {
        $scope.uploader.uploadAll();
    };

    $scope.search = function() {
        var searchKey = $scope.searchKey;
        var docs = $scope.docs;
        for(var i=0; i < docs.length; i++)  {
            if(searchKey == ""){
                docs[i]['hidden'] = false;
                continue;
            }
            if(new RegExp(searchKey).test(docs[i].name)) {
                docs[i]['hidden'] = false;
            } else {
                docs[i]['hidden'] = true;
            }
        }

    };

    //init
    var init = function () {
        $scope.uploader = new FileUploader({
            url : 'doc/upload',
            onSuccessItem : function(item, result) {
                if(result.success == true) {
                    $('#myModal').modal('hide');
                    $scope.docs.unshift(result.data);
                    $scope.uploadDoc = {};
                }else{
                    alert(result.message);
                }
            },
            onBeforeUploadItem : function(item) {
                item.formData = [{name:$scope.uploadDoc.name}, {path:$scope.uploadDoc.path}];
            },
            onAfterAddingFile : function (item) {
                $scope.uploadDoc['name'] = item.file.name;
            }
        });
        $scope.list();
    }();

});