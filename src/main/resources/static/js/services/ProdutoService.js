app.factory('ProdutoService', ['$http', function($http) { 
  return $http.get('http://localhost:8080/produtos') 
            .success(function(data) { 
              return data; 
            }) 
            .error(function(err) { 
              return err; 
            }); 
}]);