function produtoService($http, APP_CONFIG) {
	
	var rest_url = APP_CONFIG.REST_BASE_URL;
	
	this.findAll = function() {
		return $http.get(rest_url + '/produtos');
	};
	
};

var depends = [
	'$http',
	'APP_CONFIG',
	produtoService  
];

app.service('produtoService', depends);
