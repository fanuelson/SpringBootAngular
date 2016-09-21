function produtoService($http, APP_CONFIG) {
	
	var rest_url = APP_CONFIG.REST_BASE_URL;
	
	this.findAll = function() {
		return $http.get(rest_url + '/produtos');
	};
	
	this.findAllPage = function(page, size) {
		var pageParams = "?page="+page+"&size="+size;
		return $http.get(rest_url + '/produtos/page' + pageParams);
	};
	
};

var depends = [
	'$http',
	'APP_CONFIG',
	produtoService  
];

app.service('produtoService', depends);
