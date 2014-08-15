app.controller('WebDocController', function ($scope, $http, FileUploader) {

    $scope.docs = [];    //index doc list
    $scope.uploadDoc = {name: '', path: '', file: ''};
    $scope.uploadForm = {nameColor:'', nameSign:'', nameTip:''};
    $scope.folder = {name: ''};
    $scope.currentFolderId = 0;

    $scope.list = function () {
        $http.get('doc/list').success(function (data) {
            for (var i = 0; i < data.length; i++) {
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

    $scope.search = function () {
        var searchKey = $scope.searchKey;
        var docs = $scope.docs;
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
        $scope.docs[i]['showActions'] = true;
    };
    $scope.hideActions = function (i) {
        $scope.docs[i]['showActions'] = false;
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
                parentId: $scope.currentFolderId
            };
            $http.post("folder/create", postData)
                .success(function (result) {
                    $scope.docs.unshift(result.data);
                });
        }
        $scope.showCreateFolderRow = false;
    };



    //init
    var init = function () {
        $scope.uploader = new FileUploader({
            url: 'doc/upload',
            onSuccessItem: function (item, result) {
                if (result.success == true) {
                    $('#myModal').modal('hide');
                    var doc = result.data;
                    //date time format
                    doc['displayUploadTime'] = new Date(doc.updateTime).format("yyyy-MM-dd hh:mm:ss");
                    doc['displaySize'] = CommonsUtils.prettySize(doc.size);

                    //add new doc record to first row
                    doc['highlight'] = "warning";
                    $scope.docs.unshift(doc);
                    $scope.uploadFormReset();
                } else {
                    alert(result.message);
                }
            },
            onBeforeUploadItem: function (item) {
                item.formData = [
                    {name: $scope.uploadDoc.name},
                    {path: $scope.uploadDoc.path}
                ];
            },
            onAfterAddingFile: function (item) {
                $scope.uploadDoc.name = item.file.name;
                $scope.uploadDoc.file = item.file;
                $scope.uploadNameValidation();
            }
        });
        $scope.list();
    }();

});