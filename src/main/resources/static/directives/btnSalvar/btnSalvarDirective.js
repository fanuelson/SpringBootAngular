var btnSalvarDirective = function() {
	return {
	    restrict: 'E',
	    transclude: true,
	    scope: {
	      'save': '&saveAction'
	    },
	    replace: true,
	    templateUrl: 'directives/btnSalvar/btnSalvarTemplate.html'
	  };
}

app.directive('btnSalvar', btnSalvarDirective);