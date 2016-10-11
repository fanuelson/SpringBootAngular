function loginController($scope, APP_CONFIG) {

	$scope.user = {};
	
	$scope.login = function() {
		console.log($scope.user.login + " " + $scope.user.senha);
		
		
	}
}

var depends = [ 
    '$scope', 
    'APP_CONFIG', 
	loginController ]

app.controller('loginController', depends);
