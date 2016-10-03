function consultaProdutosController($scope, APP_CONFIG ,produtoService) {
	
	$scope.headerMessage = "Produtos";

	$scope.pageSize = APP_CONFIG.DEFAULT_PAGE_SIZE;
	
	$scope.formProdutoLoading = false;
	$scope.tabelaProdutoLoading = true;
	
	$scope.produto = {};
	
	$scope.produtoExclusao = {};
	$scope.setProdutoExclusao = function(produto) {
		$scope.produtoExclusao = produto;
	}
	
	$scope.findAllProductsPage = function(page) {
			$scope.tabelaProdutoLoading = true;
			$promisePage = produtoService.findAllPage(page, $scope.pageSize);
			$promisePage.success(function(data) {
				$scope.produtosPage = data;
				$scope.tabelaProdutoLoading = false;
			}).error(function(data){
				$scope.tabelaProdutoLoading = false;
			});
			
	}
	
	$scope.save = function() {
		$scope.formProdutoLoading = true;
		$promiseSave = produtoService.save($scope.produto);
		
		$promiseSave.success(function(data) {
				$scope.formProdutoLoading = false;
				$scope.findAllProductsPage($scope.produtosPage.number);
				$scope.error = null;
			})
			.error(function(data){
				$scope.error = data;
				$scope.formProdutoLoading = false;
			});
		$scope.findAllProductsPage($scope.produtosPage.number);
		$scope.produto = {};
	}
	
	$scope.del = function() {
		$scope.tabelaProdutoLoading = true;
		$promiseDelete = produtoService.del($scope.produtoExclusao);
		$promiseDelete
			.success(function(data) {
				$scope.findAllProductsPage($scope.produtosPage.number);
			})
			.error(function(data){
				$scope.messageError = data.message;
			});
	}
	
	$scope.getValidatorMessageFor = function (campo) {
		if($scope.error) {
			var validacoes = $scope.error.validacoes;
			if(validacoes) {
				for(var i = 0; i < validacoes.length; i++)
				{
					if(validacoes[i].nomeCampo == campo)
					{
						
						return validacoes[i].mensagem;
					}
				}
			}
		}
		return null;
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
		$scope.tabelaProdutoLoading = false;
	});

	$scope.getPages = function(num) {
		return new Array(num);
	}
	
}

var depends = [
   '$scope',
   'APP_CONFIG',
   'produtoService',
   consultaProdutosController
]

app.controller('consultaProdutosController', depends);
