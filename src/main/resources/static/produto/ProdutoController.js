function produtoController($scope, produtoService) {
	
	$scope.headerMessage = "Produtos";

	var $promise = produtoService.findAll();

	$promise.success(function(data) {
		$scope.produtos = data;
	});
}

app.controller('produtoController', produtoController);
