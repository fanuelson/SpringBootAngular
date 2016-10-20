function consultaInsumoController($scope, APP_CONFIG, insumoService, medidaService) {

	$scope.headerMessage = "Consulta Insumos";
	
	$scope.pageSize = APP_CONFIG.DEFAULT_PAGE_SIZE;
	
	$scope.insumosPage = {};
	
	$scope.filtroPesquisa = {};
	
	$scope.insumoExclusao = {};
	
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
	
	$scope.findAllInsumosPageFilterBy = function(page) {
		if(page == $scope.insumosPage.totalPages) {
			return;
		}
		$scope.tabelaInsumosLoading = true;
		$promisePage = insumoService.findAllPageFilterBy($scope.filtroPesquisa, page, $scope.pageSize);
		$promisePage.success(function(data) {
			$scope.insumosPage = data;
			$scope.tabelaInsumosLoading = false;
		}).error(function(data){
			$scope.tabelaInsumosLoading = false;
		});
		
	}
	
	$scope.findAllMedidas = function() {
		$promiseFindAllMedida = medidaService.findAll();
		
		$promiseFindAllMedida.success(function(data) {
			$scope.medidas = data;
			$scope.formInsumoLoading = false;
		}).error(function(data) {
			$scope.formInsumoLoading = false;
			
		});
	}
	
	$scope.pesquisar = function() {
		console.log($scope.filtroPesquisa);
		$scope.findAllInsumosPageFilterBy();
	}
	
	$scope.limparFiltroPesquisa = function() {
		$scope.filtroPesquisa = {};
	}
	
	$scope.setInsumoExclusao = function(insumo) {
		$scope.insumoExclusao = insumo;
	}
	
	$scope.del = function() {
		console.log(JSON.stringify($scope.insumoExclusao));
		$scope.formInsumoLoading = true;
		$promiseDelete = insumoService.del($scope.insumoExclusao.id);
		$promiseDelete
			.success(function(data) {
				$scope.findAllInsumosPageFilterBy($scope.page);
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
	
	$scope.findAllMedidas();
	
	$scope.findAllInsumosPage(0);
	
}

var depends = [ 
    '$scope', 
    'APP_CONFIG', 
    'insumoService',
    'medidaService',
    consultaInsumoController ]

app.controller('consultaInsumoController', depends);
