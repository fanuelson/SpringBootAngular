var depends = [
	'ui.router',
	'ui.utils.masks'
];

var app = angular.module('demoApp', depends);

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

app.filter("brDecimalNumber",function(){
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
	
});

app.directive('valueResettable', function() {
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
});

app.constant(
	"APP_CONFIG", {
		"REST_BASE_URL" : "http://localhost:8080/SpringAngularApp",
		"DEFAULT_PAGE_SIZE": 10
	}
);

var routeConfig = function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider
	    .state("home", {
		      url: "/",
		      views: {
		          "starterContent":{
		              template: "<h1>HELLO<h1/>",
		          },
		          "footerContent":{
		              templateUrl: "footer.html",
		          },
		      }     
	    })
	    .state("cadastro-insumo", {
		      url: "/cadastro-insumo",
		      views: {
		          "starterContent":{
		        	  templateUrl: "insumo/cadastro-insumo.html",
		          },
		          "footerContent":{
		              templateUrl: "footer.html",
		          },
		      }     
	    })
	    .state("consulta-insumos", {
		      url: "/consulta-insumo",
		      views: {
		          "starterContent":{
		        	  templateUrl: "insumo/consulta-insumos.html",
		          },
		          "footerContent":{
		              templateUrl: "footer.html",
		          },
		      }     
	    })
	    .state("cadastro-produto", {
	      url: "/cadastro-produto",
	      views: {
	          "starterContent":{
	              templateUrl: "produto/cadastro-produto.html",
	          },
	          "footerContent":{
	              templateUrl: "footer.html",
	          },
	      }     
	    })
	    .state("consulta-produtos", {
	      url: "/consulta-produtos",
	      views: {
	          "starterContent":{
	              templateUrl: "produto/consulta-produtos.html",
	          },
	          "footerContent":{
	              templateUrl: "footer.html",
	          },
	      }     
	    });
}

var configRoute = [
	'$stateProvider', 
	'$urlRouterProvider',
	routeConfig
]


app.config(configRoute);