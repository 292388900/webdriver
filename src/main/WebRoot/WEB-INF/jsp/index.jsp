<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html ng-app="webdriver">
<head>
    <title>My Web Driver</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="static/favicon.ico">
    <link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="static/css/base.css">
    <script type="text/javascript" src="static/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="static/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="static/js/angular.min.js"></script>
    <script type="text/javascript" src="static/js/angular-file-upload.js"></script>
    <script type="text/javascript" src="static/app/base.js"></script>
    <script type="text/javascript" src="static/app/controllers/PrivateDocsController.js"></script>

</head>

<body ng-controller="PrivateDocsController">
<div class="navbar navbar-inverse  navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        chenzhouce
                        <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="account/settings"><span class="glyphicon glyphicon-cog"></span>
                            Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="logout"><span class="glyphicon glyphicon-off"></span> Log out</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="container-fluid" >
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Overview</a></li>
                <li><a href="#">Publish Zone</a></li>
                <li><a href="#">Private Zone</a></li>
                <li><a href="#">Trash</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">Nav item</a></li>
                <li><a href="">Nav item again</a></li>
                <li><a href="">One more nav</a></li>
                <li><a href="">Another nav item</a></li>
                <li><a href="">More navigation</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <span class="navbar-brand">Private Zone</span>
                    </div>

                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li>
                                <button type="button" class="btn btn-default btn-sm navbar-btn" data-toggle="modal" data-target="#uploadModal">
                                <span class="glyphicon glyphicon-cloud-upload"></span> Upload
                                </button>
                            </li>
                        </ul>
                        <form class="navbar-form navbar-right" role="search">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon"><span class="glyphicon glyphicon-search"></span></div>
                                    <input class="form-control input-sm" placeholder="Search ..." ng-model="searchKey">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </nav>
            <ol class="breadcrumb" >
                <li><a href="javascript:void(0)" ng-click="clickHome()">Home</a></li>
                <li ng-repeat="folder in history" ng-class="{active: $last}">
                    <span ng-show="$last">{{folder.name}}</span>
                    <a href="javascript:void(0)" ng-hide="$last" ng-click="clickHistory($index)">{{folder.name}}</a>
                </li>
                <li><button class="btn-link" title="Create Folder" ng-click="openCreateFolderRow()">
                    <span class="glyphicon glyphicon-plus-sign text-primary"></span>
                    </button>
                </li>

            </ol>

            <div class="table-responsive">
                <table class="table table-hover table-striped ">
                    <thead>
                    <tr>
                        <th class="col-md-5">Document Name</th>
                        <th class="col-md-2"></th>
                        <th class="col-md-1">Size</th>
                        <th class="col-md-2">Upload Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-show="showCreateFolderRow">
                        <td>
                            <div class="form-group-sm col-md-9 ">
                                <input id="folderName" class="form-control input-sm" placeholder="New Folder" ng-model="folder.name" ng-blur="createFolder()">
                            </div>
                        </td>
                        <td></td> <td></td> <td></td> <td></td>
                    </tr>
                    <tr ng-repeat="item in items" ng-hide="item.hidden" ng-mouseover="showActions($index)" ng-mouseleave="hideActions($index)" class="{{item.highlight}}">
                        <td title="{{item.name}}" style="overflow:hidden; text-overflow: ellipsis;white-space:nowrap">
                            <span class="glyphicon glyphicon-folder-open text-success" style="margin-right: 10px" ng-show="item.size === undefined"></span>
                            <span class="glyphicon glyphicon-file text-success" style="margin-right: 10px" ng-show="item.size !== undefined"></span>
                            <button class="btn-link" style="color: #333" ng-click="clickItem($index)">{{item.name}}</button>
                        </td>
                        <td>
                            <button title="Download"  class="btn-link" ng-click="" ng-show="item.showActions"><%--Fixme: change to directive--%>
                            <span class="text-danger glyphicon glyphicon-cloud-download"></span></button>
                            <button title="Star"  class="btn-link" ng-click="" ng-show="item.showActions"><%--Fixme: change to directive--%>
                            <span class="text-danger glyphicon glyphicon-star"></span></button>
                            <button title="Share"  class="btn-link" ng-click="" ng-show="item.showActions"><%--Fixme: change to directive--%>
                            <span class="text-danger glyphicon glyphicon-share-alt"></span></button>
                            <button title="Delete"  class="btn-link" ng-click="trash($index)" ng-show="item.showActions"><%--Fixme: change to directive--%>
                            <span class="text-danger glyphicon glyphicon-trash"></span></button>
                        </td>
                        <td>{{item.displaySize}}</td>
                        <td>{{item.displayUploadTime}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="uploadModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Upload Document</h4>
            </div>
            <div class="modal-body"  style="height: 200px">
                <form class="form-horizontal" role="form" >
                    <div class="form-group has-feedback has-{{uploadForm.nameColor}}">
                        <label for="name" class="col-sm-2 control-label">File Name :</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="name" ng-model="uploadDoc.name" ng-blur="uploadNameValidation()">
                            <span class="glyphicon glyphicon-{{uploadForm.nameSign}}  form-control-feedback"></span>
                            <span class="help-block">{{uploadForm.nameTip}}</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="uploadFile" class="col-sm-2 control-label">Select File :</label>
                        <div class="col-md-8">
                            <input type="file"  id="uploadFile" nv-file-select uploader="uploader" name="uploadFile"
                                   style="width: 600px; text-overflow: ellipsis;white-space:nowrap"/>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="uploadBtn" ng-click="upload()" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-cloud-upload"></span> Upload</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>
