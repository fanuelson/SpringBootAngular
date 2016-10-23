function consultaFornecedorController($scope, APP_CONFIG, fornecedorService) {

	$scope.headerPage = "Fornecedor";
	
	$scope.headerMessage = "Consulta Fornecedor";
	
	$scope.pageSize = APP_CONFIG.DEFAULT_PAGE_SIZE;
	
	$scope.fornecedorPage = {};
	
	$scope.filtroPesquisa = {};
	
	$scope.fornecedorExclusao = {};
	
	$scope.findAllPage = function(page) {
		if(page == $scope.fornecedorPage.totalPages) {
			return;
		}
		startTabelaLoading();
		$promisePage = fornecedorService.findAllPage(page, $scope.pageSize);
		$promisePage.success(function(data) {
			$scope.fornecedorPage = data;
			stopTabelaLoading();
		}).error(function(data){
			stopTabelaLoading();
		});
		
	}
	
	$scope.findAllPageFilterBy = function(page) {
		if(page == $scope.fornecedorPage.totalPages) {
			return;
		}
		startTabelaLoading();
		$promisePage = fornecedorService.findAllPageFilterBy($scope.filtroPesquisa, page, $scope.pageSize);
		$promisePage.success(function(data) {
			$scope.fornecedorPage = data;
			stopTabelaLoading();
		}).error(function(data){
			stopTabelaLoading();
		});
		
	}
	
	$scope.pesquisar = function() {
		console.log($scope.filtroPesquisa);
		$scope.findAllPageFilterBy(0);
	}
	
	$scope.limparFiltroPesquisa = function() {
		$scope.filtroPesquisa = {};
	}
	
	$scope.setFornecedorExclusao = function(forn) {
		$scope.fornecedorExclusao = forn;
	}
	
	$scope.del = function() {
		console.log(JSON.stringify($scope.fornecedorExclusao));
		startTabelaLoading();
		$promiseDelete = fornecedorService.del($scope.fornecedorExclusao.id);
		$promiseDelete
			.success(function(data) {
				$scope.findAllPageFilterBy($scope.page);
			})
			.error(function(data){
				$scope.messageError = data.message;
			});
	}
	
	$scope.getPages = function(num) {
		return new Array(num);
	}
	
	var setPageSize = function(pageSize) {
		$scope.pageSize = pageSize;
	}
	
	var startTabelaLoading = function() {
		$scope.tabelaLoading = true;
	}
	
	var stopTabelaLoading = function() {
		$scope.tabelaLoading = false;
	}
	
	$scope.findAllPage(0);
	
}

var depends = [ 
    '$scope', 
    'APP_CONFIG', 
    'fornecedorService',
    consultaFornecedorController ]

app.controller('consultaFornecedorController', depends);
