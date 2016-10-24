function fornecedorService($http, APP_CONFIG) {
	
	var rest_url = APP_CONFIG.REST_BASE_URL;
	
	this.save = function(fornecedor) {
		return $http.post(rest_url + '/fornecedores', fornecedor);
	};
	
	this.findAllPage = function(page, size) {
		var pageParams = "?page="+page+"&size="+size;
		return $http.get(rest_url + '/fornecedores/page' + pageParams);
	};
	
	this.findAllPageFilterBy = function(filtro, page, size, sort) {
		var pageParams = "?page="+page+"&size="+size+"&sort="+sort;
		return $http.post(rest_url + '/fornecedores/page/filterBy' + pageParams, filtro);
	};
	
	this.del = function(idForn) {
		return $http.delete(rest_url + '/fornecedores/' + idForn);
	};
	
};

var depends = [
	'$http',
	'APP_CONFIG',
	fornecedorService  
];

app.service('fornecedorService', depends);
