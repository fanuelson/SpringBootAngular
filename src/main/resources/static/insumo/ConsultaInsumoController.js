function consultaInsumoController($scope, APP_CONFIG, insumoService) {

	$scope.headerMessage = "Consulta Insumos";
	
	$scope.pageSize = APP_CONFIG.DEFAULT_PAGE_SIZE;
	
	$scope.insumosPage = {};
	
	$scope.findAllInsumosPage = function(page) {
		if(page == $scope.insumosPage.totalPages) {
			return;
		}
		$scope.tabelaInsumosLoading = true;
		$promisePage = insumoService.findAllPage(page, $scope.pageSize);
		$promisePage.success(function(data) {
			$scope.insumosPage = data;
			$scope.tabelaInsumosLoading = false;
		}).error(function(data){
			$scope.tabelaInsumosLoading = false;
		});
		
	}
	
	$scope.getPages = function(num) {
		return new Array(num);
	}
	
	var setPageSize = function(pageSize) {
		$scope.pageSize = pageSize;
	}
	
	$scope.findAllInsumosPage(0);
	
}

var depends = [ 
    '$scope', 
    'APP_CONFIG', 
    'insumoService',
    consultaInsumoController ]

app.controller('consultaInsumoController', depends);
