var btnPesquisarDirective = function() {
	return {
	    restrict: 'E',
	    transclude: true,
	    scope: {
	      'pesquisar': '&pesquisaAction'
	    },
	    replace: true,
	    templateUrl: 'directives/btnPesquisar/btnPesquisarTemplate.html'
	  };
}

app.directive('btnPesquisar', btnPesquisarDirective);