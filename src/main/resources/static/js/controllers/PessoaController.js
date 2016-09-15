app.controller('PessoaController', ['$scope', 'PessoaService' ,function($scope, PessoaService) { 
   
	$scope.welcomeMessage = "Hello";
	
	PessoaService.success(function(data) {
		$scope.pessoas = data;
	});
  
}]);
