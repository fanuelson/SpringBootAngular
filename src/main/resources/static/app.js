var depends = [
	'ui.router'
];

var app = angular.module('demoApp', depends);

app.constant("APP_CONFIG", {
	"REST_BASE_URL" : "http://localhost:8080/SpringAngularApp",
	"DEFAULT_PAGE_SIZE": 10
});

var routeConfig = function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider
	    .state("produtos", {
	      url: "/",
	      views: {
	          "starterContent":{
	              templateUrl: "produto/produto.html",
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