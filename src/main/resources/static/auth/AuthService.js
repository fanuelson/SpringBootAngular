function authService($window) {
	var self = this;
	
	self.saveToken = function(token) {
		$window.localStorage['jwtToken'] = token;
	}
	
	self.removeToken = function() {
		$window.localStorage.removeItem['jwtToken'];
	}
	
	self.getToken = function() {
		return $window.localStorage['jwtToken'];
	}
}
	
var depends = [
  '$window',
  authService
];
	
app.service('authService', depends);