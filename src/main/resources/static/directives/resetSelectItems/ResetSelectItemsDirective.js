(function() {

   'use strict'

   var resetSelectItemsDirective = function() {
      return {
         restrict: 'A',
         link: function (scope, element, attrs) {
            scope.$watch(attrs.ngModel, function (v) {
               if(!v) {
                  $(element).dropdown('restore defaults');
               }
            });
         }

      };
   }

   angular.module('demoApp')
      .directive('valueResettable', resetSelectItemsDirective);
})();
