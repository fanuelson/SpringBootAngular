function consultaFornecedorController($scope, APP_CONFIG, fornecedorService) {

	$scope.headerPage = "Fornecedor";
	
	$scope.headerMessage = "Consulta Fornecedor";
	
	$scope.pageSize = APP_CONFIG.DEFAULT_PAGE_SIZE;
	
	$scope.fornecedorPage = {};
	
	$scope.filtroPesquisa = {};
	
	$scope.fornecedorExclusao = {};
	
	$scope.sortBy = {
		field: null,
		order: null
	};
	
	$scope.isAsc = function(field){
		return $scope.sortBy.order && $scope.sortBy.field == field &&  $scope.sortBy.order == 'ASC' ;
	}
	
	$scope.isDesc = function(field){
		return $scope.sortBy.order && $scope.sortBy.field == field && $scope.sortBy.order == 'DESC' ;
	}
	
	$scope.findAllPageFilterBySortBy = function(field) {
		console.log(field);
		$scope.sortBy.field = field;
		if($scope.sortBy.order  && $scope.sortBy.order == 'ASC') {
			$scope.sortBy.order = 'DESC'; 
		}else{
			$scope.sortBy.order = 'ASC'; 
		}
		$scope.findAllPageFilterBy($scope.fornecedorPage.number);
	}
	
	var getSortByQuery = function () {
		if($scope.sortBy.field && $scope.sortBy.order){
			return $scope.sortBy.field + "," + $scope.sortBy.order;
		}
		return "";
	}
	
	$scope.findAllPageFilterBy = function(page) {
		startTabelaLoading();
		$promisePage = fornecedorService.findAllPageFilterBy($scope.filtroPesquisa, page, $scope.pageSize, getSortByQuery());
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
		$scope.sortBy = {};
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
	
	$scope.findAllPageFilterBy(0);
	
}

var depends = [ 
    '$scope', 
    'APP_CONFIG', 
    'fornecedorService',
    consultaFornecedorController ]

app.controller('consultaFornecedorController', depends);
