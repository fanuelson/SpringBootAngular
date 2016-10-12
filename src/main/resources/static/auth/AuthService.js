function authService($http, APP_CONFIG) {
	var self = this;
	
	self.login = function(user) {
		return $http.post(APP_CONFIG.REST_BASE_URL + '/auth/login', user);
	}
	
}
	
var depends = [
  '$http',
  'APP_CONFIG',
  authService
];
	
app.service('authService', depends);