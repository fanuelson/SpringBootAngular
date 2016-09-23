function produtoController($scope, APP_CONFIG ,produtoService) {
	
	$scope.headerMessage = "Produtos";

	$scope.pageSize = APP_CONFIG.DEFAULT_PAGE_SIZE;
	
	$scope.produto = {};
	
	$scope.produtoExclusao = {};
	$scope.setProdutoExclusao = function(produto) {
		$scope.produtoExclusao = produto;
	}
	
	$scope.findAllProductsPage = function(page) {
			$promisePage = produtoService.findAllPage(page, $scope.pageSize);
			$promisePage.success(function(data) {
				$scope.produtosPage = data;
			});
	}
	
	$scope.save = function() {
		$promiseSave = produtoService.save($scope.produto);
		$promiseSave
			.success(function(data) {
				
				$scope.findAllProductsPage($scope.produtosPage.number);
				$scope.messageError = null;
			})
			.error(function(data){
				$scope.messageError = data.message;
			});
		$scope.findAllProductsPage($scope.produtosPage.number);
		$scope.produto = {};
	}
	
	$scope.del = function() {
		$promiseDelete = produtoService.del($scope.produtoExclusao);
		$promiseDelete
			.success(function(data) {
				$scope.findAllProductsPage($scope.produtosPage.number);
			})
			.error(function(data){
				$scope.messageError = data.message;
			});
	}
	
	$scope.limparCampos = function() {
		$scope.produto = {};
		$scope.messageError = null;
	}
	
	
	var setPageSize = function(pageSize) {
		$scope.pageSize = pageSize;
	}
	
	var $promisePage = produtoService.findAllPage(0, $scope.pageSize);
	
	$promisePage.success(function(data) {
		$scope.produtosPage = data;
	});

	$scope.getPages = function(num) {
		return new Array(num);
	}
}

var depends = [
   '$scope',
   'APP_CONFIG',
   'produtoService',
   produtoController
]

app.controller('produtoController', depends);
