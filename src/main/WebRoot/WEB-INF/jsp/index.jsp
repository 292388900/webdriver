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
    <script type="text/javascript" src="static/js/angularjs/angular.js"></script>
    <script type="text/javascript" src="static/js/angularjs/angular-route.js"></script>
    <script type="text/javascript" src="static/js/angular-file-upload.js"></script>
    <%--app js files--%>
    <script type="text/javascript" src="app/base.js"></script>
    <script type="text/javascript" src="app/filters.js"></script>
    <script type="text/javascript" src="app/controller/MainController.js"></script>
    <script type="text/javascript" src="app/controller/PrivateZoneController.js"></script>

</head>

<body ng-controller="MainController">
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
                <li><a href="#/test">Publish Zone</a></li>
                <li><a href="#/private">Private Zone</a></li>
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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2" ng-view></div>
    </div>
</div>




</body>
</html>
