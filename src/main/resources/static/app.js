var depends = [
	'ui.router'
];

var app = angular.module('demoApp', depends);

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
		          "menuContent":{
		              templateUrl: "menu.html",
		          },
		          "starterContent":{
		              template: "<h1>HELLO<h1/>",
		          },
		          "footerContent":{
		              templateUrl: "footer.html",
		          },
		      }     
	    })
	    .state("produtos", {
	      url: "/produtos",
	      views: {
	          "menuContent":{
	              templateUrl: "menu.html",
	          },
	          "starterContent":{
	              templateUrl: "produto/produto.html",
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