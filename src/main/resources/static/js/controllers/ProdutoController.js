app.controller('ProdutoController', ['$scope', 'ProdutoService' ,function($scope, ProdutoService) { 
   
	$scope.headerMessage = "Produtos";
	
	ProdutoService.success(function(data) {
		$scope.produtos = data;
	});
  
}]);
