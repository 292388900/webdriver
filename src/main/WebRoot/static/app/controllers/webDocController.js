app.controller('WebDocController', function ($scope, $http, FileUploader) {

    $scope.docs = [];    //index doc list
    $scope.uploadDoc={};    //upload form

    $scope.list = function () {
        $http.get('doc/list').success(function (data) {
            for(var i=0; i < data.length; i++) {
                data[i]['displayUploadTime'] = new Date(data[i].updateTime).format("yyyy-MM-dd hh:mm:ss");
                data[i]['displaySize'] = CommonsUtils.prettySize(data[i].size);
            }
            $scope.docs = data;
        });
    };

    $scope.trash = function (i) {
        var id = $scope.docs[i].id;
        $http.post('doc/trash/' + id).success(function (result) {
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

    $scope.showActions = function(i){
        $scope.docs[i]['showActions'] = true;
    };
    $scope.hideActions = function(i){
        $scope.docs[i]['showActions'] = false;
    };

    //init
    var init = function () {
        $scope.uploader = new FileUploader({
            url : 'doc/upload',
            onSuccessItem : function(item, result) {
                if(result.success == true) {
                    $('#myModal').modal('hide');
                    var doc = result.data;
                    //date time format
                    doc['displayUploadTime'] = new Date(doc.updateTime).format("yyyy-MM-dd hh:mm:ss");
                    doc['displaySize'] = CommonsUtils.prettySize(doc.size);

                    //add new doc record to first row
                    doc['highlight'] = "warning";
                    $scope.docs.unshift(doc);
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