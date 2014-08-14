var directives = angular.module('directives', []);
directives.directive('showonhoverparent',
    function () {
        return {
            link: function (scope, element, attrs) {
                element.parent().parent().bind('mouseenter', function () {
                    element.show();
                });
                element.parent().parent().bind('mouseleave', function () {
                    element.hide();
                });
            }
        };
    });