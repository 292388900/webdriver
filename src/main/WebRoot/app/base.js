app = angular.module('webdriver',['ngRoute','angularFileUpload']);
app.config(function($httpProvider){
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.transformRequest = function (data) {
        return data?$.param(data):null;
    };
});

app.config(function($routeProvider) {
    $routeProvider
        .when('/private', {controller:'PrivateZoneController', templateUrl: 'private'});
});
