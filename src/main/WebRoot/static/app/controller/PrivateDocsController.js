app.controller('PrivateDocsController', function ($scope, $http, FileUploader) {

    $scope.items = [];    //item table
    $scope.uploadDoc = {name: '', path: '', file: ''}; // doc in upload form
    $scope.uploadForm = {nameColor:'', nameSign:'', nameTip:''};
    $scope.folder = {name: ''}; // folder for creating
    $scope.currentFolderId = 1;
    $scope.currentFolderPath = "/";
    $scope.history = [];

    $scope.list = function () {
        var postData = {
            parentId : $scope.currentFolderId,
            isRemoved : false
        };
        $http.post('doc/list', postData).success(function (data) {
            $scope.items = data;
        });
    };

    $scope.trash = function (i) {
        var item = $scope.items[i];
        var id = $scope.items[i].id;
        if(item.size !== undefined) {
            //item is a file
            $http.get('doc/delete/' + id).success(function (result) {
                if (result.success == true) {
                    $scope.items.splice(i, 1);
                } else {
                    alert("error");
                }
            });
        }else{
            //item is a folder
            $http.get('folder/delete/' + id).success(function (result) {
                if (result.success == true) {
                    $scope.items.splice(i, 1);
                } else {
                    alert("error");
                }
            });
        }
    };

    $scope.upload = function () {
        $scope.uploader.uploadAll();
    };

    $scope.search = function () {
        var searchKey = $scope.searchKey;
        var docs = $scope.items;
        for (var i = 0; i < docs.length; i++) {
            if (searchKey == "") {
                docs[i]['hidden'] = false;
                continue;
            }
            if (new RegExp(searchKey).test(docs[i].name)) {
                docs[i]['hidden'] = false;
            } else {
                docs[i]['hidden'] = true;
            }
        }
    };

    $scope.showActions = function (i) {
        $scope.items[i]['showActions'] = true;
    };
    $scope.hideActions = function (i) {
        $scope.items[i]['showActions'] = false;
    };

    $scope.uploadNameValidation = function () {
        //Name field is empty
        if ($scope.uploadDoc.name == "") {
            $scope.uploadForm.nameColor = "warning";
            $scope.uploadForm.nameSign = "warning-sign";
            $scope.uploadForm.nameTip = "If name is empty, the original file name will be used.";
            return;
        }

        //Name field suffix is different from file name suffix
        if ($scope.uploadDoc.file != "" && $scope.uploadDoc.name.indexOf(".") != -1) {
            var nameInputSuffix = $scope.uploadDoc.name.split(".").pop();
            var fileSuffix = $scope.uploadDoc.file.name.split(".").pop();
            if (nameInputSuffix != fileSuffix) {
                $scope.uploadForm.nameColor = "error";
                $scope.uploadForm.nameSign = "remove";
                $scope.uploadForm.nameTip = "Please enter correct file name suffix.";
                return;
            }
        }


        $scope.uploadForm.nameColor = "success";
        $scope.uploadForm.nameSign = "ok";
        $scope.uploadForm.nameTip = "";
    };

    $scope.uploadFormReset = function(){
        $scope.uploadDoc = {name: '', path: '', file: ''};
        $scope.uploadForm = {nameColor:'', nameSign:'', nameTip:''};
    };

    $scope.openCreateFolderRow = function(){
        $scope.showCreateFolderRow = true;
        setTimeout(function(){
            $('#folderName').focus();
        },300);
    };

    $scope.createFolder = function(){
        if ($scope.folder.name != "") {
            var postData = {
                name: $scope.folder.name,
                parentId : $scope.currentFolderId
            };
            $http.post("folder/create", postData)
                .success(function (result) {
                    if(result.success == true) {
                        $scope.items.unshift(result.data);
                    }else {
                        alert(result.message);
                    }
            });
        }
        $scope.folder.name = "";
        $scope.showCreateFolderRow = false;
    };

    $scope.clickItem = function(i){
        var item = $scope.items[i];
        if(item.size !== undefined) {
            //item is a file

        } else {
            //item is a folder
            $scope.currentFolderPath += (item.name + "/");
            $scope.currentFolderId = item.id;
            $scope.history.push(item);
            $scope.list();
        }
    };

    $scope.clickHistory = function(i) {
        $scope.history.splice(i + 1);

        var item = $scope.history[i];
        var tempNameArr = [];
        for(var i=0; i<$scope.history.length; i++) {
            tempNameArr[i] = $scope.history[i].name;
        }
        $scope.currentFolderPath = "/" + tempNameArr.join("/") + "/";
        $scope.currentFolderId = item.id;
        $scope.list();
    };

    $scope.clickHome = function() {
        $scope.currentFolderId = 1;
        $scope.currentFolderPath = "/";
        $scope.history = [];
        $scope.list();
    };



    //init
    var init = function () {
        $scope.uploader = new FileUploader({
            url: 'doc/upload',
            onSuccessItem: function (item, result) {
                if (result.success == true) {
                    $('#uploadModal').modal('hide');
                    var doc = result.data;

                    //add new doc record to first row
                    doc['highlight'] = "warning";
                    $scope.items.unshift(doc);
                    $scope.uploadFormReset();
                } else {
                    alert(result.message);
                }
            },
            onBeforeUploadItem: function (item) {
                item.formData = [
                    {name: $scope.uploadDoc.name},
                    {folderId: $scope.currentFolderId}
                ];
            },
            onAfterAddingFile: function (item) {
                $scope.uploadDoc.name = item.file.name;
                $scope.uploadDoc.file = item.file;
                $scope.uploadNameValidation();
            }
        });
        $scope.list();
        $scope.$watch("searchKey", $scope.search);

        $('#uploadModal').on('hidden.bs.modal', $scope.uploadFormReset);
    }();

});