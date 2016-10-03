function insumoService($http, APP_CONFIG) {
	
	var rest_url = APP_CONFIG.REST_BASE_URL;
	
	this.findAll = function() {
		return $http.get(rest_url + '/insumos');
	};

	this.findAllPage = function(page, size) {
		var pageParams = "?page="+page+"&size="+size;
		return $http.get(rest_url + '/insumos/page' + pageParams);
	};
	
	this.save = function(insumo) {
		return $http.post(rest_url + '/insumos', insumo);
	};
	
};

var depends = [
	'$http',
	'APP_CONFIG',
	insumoService  
];

app.service('insumoService', depends);
