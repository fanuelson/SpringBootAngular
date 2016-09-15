app.factory('PessoaService', ['$http', function($http) { 
  return $http.get('http://localhost:8080/pessoas') 
            .success(function(data) { 
              return data; 
            }) 
            .error(function(err) { 
              return err; 
            }); 
}]);