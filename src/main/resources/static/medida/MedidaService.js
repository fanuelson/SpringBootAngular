function medidaService($http, APP_CONFIG) {
	
	var rest_url = APP_CONFIG.REST_BASE_URL;
	
	this.findAll = function(page, size) {
		return $http.get(rest_url + '/medidas');
	};
	
};

var depends = [
	'$http',
	'APP_CONFIG',
	medidaService  
];

app.service('medidaService', depends);
