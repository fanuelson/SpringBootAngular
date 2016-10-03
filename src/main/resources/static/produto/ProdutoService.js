function produtoService($http, APP_CONFIG) {
	
	var rest_url = APP_CONFIG.REST_BASE_URL;
	
	this.findAllPage = function(page, size) {
		var pageParams = "?page="+page+"&size="+size;
		return $http.get(rest_url + '/produtos/page' + pageParams);
	};
	
	this.save = function(produto) {
		return $http.post(rest_url + '/produtos', produto);
	};
	
	this.del = function(produto) {
		return $http.delete(rest_url + "/produtos/" + produto.idProduto);
	};
	
};

var depends = [
	'$http',
	'APP_CONFIG',
	produtoService  
];

app.service('produtoService', depends);
