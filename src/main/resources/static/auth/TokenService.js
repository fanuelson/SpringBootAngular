function tokenService($window) {
	var self = this;
	
	self.saveToken = function(token) {
		$window.localStorage['jwtToken'] = token;
	}
	
	self.removeToken = function() {
		$window.localStorage.removeItem('jwtToken');
	}
	
	self.getToken = function() {
		return $window.localStorage['jwtToken'];
	}
	
	self.hasToken = function() {
		return $window.localStorage['jwtToken'] ? true : false;
	}
}
	
var depends = [
  '$window',
  tokenService
];
	
app.service('tokenService', depends);