function authInterceptor(APP_CONFIG, authService) {
	return {
		// automatically attach Authorization header
		request : function(config) {
			var token = authService.getToken();
			if(token) {
				config.headers.Authorization = 'Bearer ' + token;
			}
			
			return config;
		},

		// If a token was sent back, save it
		response : function(res) {
			return res;
		}
	}
}


var depends = [
  'APP_CONFIG',
  'authService',
  authInterceptor
];

app.factory('authInterceptor', depends);

app.config(function($httpProvider) {
  $httpProvider.interceptors.push('authInterceptor');
});