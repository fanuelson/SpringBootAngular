function produtoController($scope, APP_CONFIG ,produtoService) {
	
	$scope.headerMessage = "Produtos";

	$scope.pageSize = APP_CONFIG.DEFAULT_PAGE_SIZE;
	
	$scope.findAllProductsPage = function(page) {
		if(isPageValid(page)){
			$promisePage = produtoService.findAllPage(page, $scope.pageSize);
			$promisePage.success(function(data) {
				$scope.produtosPage = data;
			});
		}
	}
	
	var isPageValid = function(page) {
		return page != -1 
				&& page != $scope.produtosPage.totalPages 
				&& page != $scope.produtosPage.number
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
