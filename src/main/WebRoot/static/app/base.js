app = angular.module('webdriver',['angularFileUpload']);
app.config(function($httpProvider){
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.transformRequest = function (data) {
        return data?$.param(data):null;
    };
});