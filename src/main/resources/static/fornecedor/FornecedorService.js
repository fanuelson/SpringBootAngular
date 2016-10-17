function fornecedorService($http, APP_CONFIG) {
	
	var rest_url = APP_CONFIG.REST_BASE_URL;
	
	this.save = function(fornecedor) {
		return $http.post(rest_url + '/fornecedores', fornecedor);
	};
	
};

var depends = [
	'$http',
	'APP_CONFIG',
	fornecedorService  
];

app.service('fornecedorService', depends);
