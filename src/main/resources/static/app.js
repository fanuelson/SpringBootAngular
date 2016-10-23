var depends = [
	'ui.router',
	'ui.utils.masks',
	'idf.br-filters'
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
		"REST_BASE_URL" : "http://localhost:8080/SpringAngularApp/api",
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
		    .state("cadastro-fornecedor", {
		    	url: "/cadastro-fornecedor",
		    	views: {
		    		"starterContent":{
		    			templateUrl: "fornecedor/cadastro-fornecedor.html"
		    		}
		    	}     
		    })
		    .state("consulta-fornecedor", {
		    	url: "/consulta-fornecedor",
		    	views: {
		    		"starterContent":{
		    			templateUrl: "fornecedor/consulta-fornecedor.html"
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
	
/* ROUTE AUTH LISTENER */
	
	app.run(function ($rootScope, $state, $location, tokenService) {
		  
	    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState) {
	      
	    	if(toState.name != 'login') {
	    		if(!tokenService.hasToken()) {
	    			$state.go('login');
	    	        event.preventDefault();
	    	        return;
	    		}
	    	}
	    		
	      
	    });
	});
	
/* END ROUTE AUTH LISTENER */

