function produtoController($scope, APP_CONFIG ,produtoService) {
	
	$scope.headerMessage = "Produtos";

	$scope.pageSize = APP_CONFIG.DEFAULT_PAGE_SIZE;
	
	$scope.produto = {};
	
	$scope.findAllProductsPage = function(page) {
		if(isPageValid(page)){
			$promisePage = produtoService.findAllPage(page, $scope.pageSize);
			$promisePage.success(function(data) {
				$scope.produtosPage = data;
			});
		}
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
		$scope.produto = {};
	}
	
	$scope.del = function(produto) {
		$promiseDelete = produtoService.del(produto);
		$promiseDelete
			.success(function(data) {
				$scope.findAllProductsPage($scope.produtosPage.number);
				$scope.messageError = null;
			})
			.error(function(data){
				$scope.messageError = data.message;
			});
	}
	
	
	var isPageValid = function(page) {
		return page != -1 
				&& page != $scope.produtosPage.totalPages;
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
