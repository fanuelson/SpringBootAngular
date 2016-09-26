var depends = [
];

var app = angular.module('demoApp', depends);

app.constant("APP_CONFIG", {
	"REST_BASE_URL" : "http://localhost:8080",
	"DEFAULT_PAGE_SIZE": 10
});
