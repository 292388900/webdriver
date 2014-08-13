<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<html ng-app="myApp">
<head>
    <title>My Web Driver</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap-theme.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/dashboard.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/jquery.fileupload.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/jquery.fileupload-ui.css">
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/doc.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.iframe-transport.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/angular.min.js"></script>
    <script type="text/javascript">
        app = angular.module('myApp',[]);
    </script>
    <script type="text/javascript" src="${ctx}/static/app/controllers/webDocController.js"></script>
    <style>
        body {
            padding-top: 50px;
        }

    </style>
</head>
<body ng-controller="WebDocController">
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
                <li class="active"><a href="${ctx}/">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        chenzhouce
                        <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/account/settings"><span class="glyphicon glyphicon-cog"></span>
                            Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="${ctx}/logout"><span class="glyphicon glyphicon-off"></span> Log out</a></li>
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
            <nav class="navbar" role="navigation">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse">
                        <button type="button" class="btn btn-success navbar-btn" data-toggle="modal"
                                data-target="#myModal"><span
                                class="glyphicon glyphicon-cloud-upload"></span> Upload
                        </button>
                        <form class="navbar-form navbar-right" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>
                                Search
                            </button>
                        </form>
                    </div>
                </div>
            </nav>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th class="col-md-6">Doc Name</th>
                    <th>Size</th>
                    <th>Author</th>
                    <th>Directory</th>
                    <th>Upload Time</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="doc in docs">
                    <td>{{doc.name}}<a href="javascript:void(0)" ng-click="remove($index)"><span
                            class="glyphicon glyphicon-trash pull-right"></span></a></td>
                    <td>{{doc.size}}</td>
                    <td>{{doc.user.username}}</td>
                    <td>{{doc.path}}</td>
                    <td>{{doc.uploadTime}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Upload Document</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="test" >
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">Name :</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="name" ng-model="doc.name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="uploadFile" class="col-sm-2 control-label">File :</label>
                        <div class="col-md-8">
                            <input type="file"  id="uploadFile" name="Files[]" data-url="doc/upload" multiple/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="path" class="col-sm-2 control-label">Path :</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="path" ng-model="doc.path">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" ng-click="upload()"><span class="glyphicon glyphicon-cloud-upload"></span> Upload</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>
