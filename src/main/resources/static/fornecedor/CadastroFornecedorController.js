function cadastroFornecedorController($scope, fornecedorService) {

	$scope.headerMessage = "Cadastro de Fornecedor";

	$scope.formFornecedorLoading = false;

	$scope.fornecedor = {};

	$scope.save = function() {
		$scope.retornoSucesso = {};
		$scope.formFornecedorLoading = true;

		$scope.fornecedor.celular = parseInt($scope.fornecedor.celular);
		$scope.fornecedor.telefone = parseInt($scope.fornecedor.telefone);

		$promiseSave = fornecedorService.save($scope.fornecedor);

		$promiseSave.success(function(data) {
			$scope.formFornecedorLoading = false;
			$scope.error = null;
			$scope.fornecedor = {};
			$scope.retornoSucesso = data;
		}).error(function(data) {
			$scope.formFornecedorLoading = false;
			$scope.error = data;
		});
	}

	$scope.limparCampos = function() {
		$scope.insumo = {};
		$scope.error = {};
		$scope.formFornecedorLoading = false;
		$scope.retornoSucesso = {};
	}

}

var depends = [
    '$scope',
    'fornecedorService',
    cadastroFornecedorController ]

app.controller('cadastroFornecedorController', depends);
