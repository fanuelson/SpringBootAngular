function cadastroInsumoController($scope, APP_CONFIG, insumoService, medidaService) {

	$scope.headerMessage = "Cadastro de Insumo";

	$scope.formInsumoLoading = true;
	
	$scope.insumo = {};
	
	$scope.save = function() {
		$scope.formInsumoLoading = true;
		console.log($scope.insumo.medida);
		$promiseSave = insumoService.save($scope.insumo);

		$promiseSave.success(function(data) {
			$scope.formInsumoLoading = false;
			$scope.error = null;
			$scope.insumo = {};
			$scope.retornoSucesso = data;
		}).error(function(data) {
			$scope.limparCampos();
			$scope.formInsumoLoading = false;
			$scope.error = data;
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
	
	$scope.getValidatorMessageForCampoObrigatorio = function(campo) {
		if ($scope.error) {
			var validacoes = $scope.error.validacoes;
			if (validacoes) {
				for (var i = 0; i < validacoes.length; i++) {
					if (validacoes[i].nomeCampo == campo) {
						return validacoes[i].mensagem;
					}
				}
			}
		}
		return null;
	}
	
	$scope.limparCampos = function() {
		$scope.insumo = {};
		$scope.error = {};
		$scope.findAllMedidas();
		$scope.formInsumoLoading = false;
		$scope.retornoSucesso = {};
	}
	
	$scope.findAllMedidas();
}

var depends = [ 
    '$scope', 
    'APP_CONFIG', 
    'insumoService',
    'medidaService',
	cadastroInsumoController ]

app.controller('cadastroInsumoController', depends);
