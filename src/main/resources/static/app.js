var depends = [
	'ui.router',
	'ui.utils.masks'
];

var app = angular.module('demoApp', depends);

/* -- BEGIN FILTERs -- */

	var colocarPontosMilhar = function(num) {
		var numAux = ""+num;
		var i;
		var casa3 = 0;
		var result = "";
		for(i = numAux.length - 1; i >= 0; i-- ){
			if(casa3 == 3){
				result = result + ".";
				casa3 = -1;
			}
			result = result + numAux[i];
			casa3++;
		}
		return result.split('').reverse().join('');
	}
	
	var brDecimalNumberFilter = function(){
		return function(input) {
			if(angular.isNumber(input)){
				var inputAux = ""+input;
				var inputPartes = inputAux.split(".");
				var result = inputPartes[0].replace(/,/g, ".");
				result = colocarPontosMilhar(result);
				if(inputPartes[1]) {
					result = result + "," + inputPartes[1];
				}
				return result;
			} else {
				return null;
			}
		};
		
	}
	
	app.filter("brDecimalNumber", brDecimalNumberFilter);

/* -- END FILTERs -- */

/* -- BEGIN DIRECTIVEs -- */

	var resetSelectItemsDirective = function() {
	  return {
		restrict: 'A',
	    link: function (scope, element, attrs) {
	        scope.$watch(attrs.ngModel, function (v) {
	            if(!v) {
	            	$(element).dropdown('restore defaults');
	            }
	        });
	    }
	  };
	}
	
	app.directive('valueResettable', resetSelectItemsDirective);

/* -- END DIRECTIVEs -- */

app.constant(
	"APP_CONFIG", {
		"REST_BASE_URL" : "http://localhost:8080/SpringAngularApp",
		"DEFAULT_PAGE_SIZE": 10
	}
);


/* -- ROUTE CONFIG -- */

	var routeConfig = function($stateProvider, $urlRouterProvider) {
	    
		$urlRouterProvider.otherwise('/');
	    
	    $stateProvider
		    .state("home", {
		    	url: "/",
		    	views: {
		    		"starterContent":{
		    			template: "<h1>HELLO<h1/>"
		    		}
		    	}     
		    })
		    .state("login", {
		    	url: "/login",
		    	views: {
		    		"starterContent":{
			        	  templateUrl: "login/login.html"
			        }
		    	}     
		    })
		    .state("cadastro-insumo", {
			      url: "/cadastro-insumo",
			      views: {
			          "starterContent":{
			        	  templateUrl: "insumo/cadastro-insumo.html"
			          }
			      }     
		    })
		    .state("consulta-insumos", {
			      url: "/consulta-insumo",
			      views: {
			          "starterContent":{
			        	  templateUrl: "insumo/consulta-insumos.html"
			          }
			      }     
		    })
		    .state("cadastro-produto", {
			      url: "/cadastro-produto",
			      views: {
			          "starterContent":{
			              templateUrl: "produto/cadastro-produto.html"
			          }
			      }     
		    })
		    .state("consulta-produtos", {
			      url: "/consulta-produtos",
			      views: {
			          "starterContent":{
			              templateUrl: "produto/consulta-produtos.html"
			          }
			      }     
		    });
	}
	
	var configRoute = [
		'$stateProvider', 
		'$urlRouterProvider',
		routeConfig
	]
	
	app.config(configRoute);

/* -- END ROUTE CONFIG -- */
	
<<<<<<< Updated upstream
=======
/* -- AUTH SERVICE -- */
	
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

/* -- END AUTH SERVICE -- */

/* -- AUTH INTERCEPTOR -- */

	function authInterceptor(APP_CONFIG, authService, $injector, $q) {
		return {
			// automatically attach Authorization header
			request : function(config) {
				var token = authService.getToken();
				if(token) {
					config.headers.Authorization = 'Bearer ' + token;
				}
				
				return config;
			},
			 // optional method
			requestError: function(rejection) {
		      // do something on error
		      if (canRecover(rejection)) {
		        return responseOrNewPromise
		      }
		      return $q.reject(rejection);
		    },
	
			// If a token was sent back, save it
			response : function(res) {
				var refreshToken = res.headers('refresh-token');
				if(refreshToken) {
					authService.saveToken(refreshToken);
				}
				return res;
			},
			
			responseError : function(res) {
				authService.removeToken();
				$injector.get('$state').transitionTo('login');
				return $q.reject(res);
			}
		}
	}
	

	var depends = [
	  'APP_CONFIG',
	  'authService',
	  '$injector',
	  '$q',
	  authInterceptor
	];
	
	app.factory('authInterceptor', depends);
	
	app.config(function($httpProvider) {
	  $httpProvider.interceptors.push('authInterceptor');
	});

/* -- END AUTH INTERCEPTOR -- */
>>>>>>> Stashed changes
