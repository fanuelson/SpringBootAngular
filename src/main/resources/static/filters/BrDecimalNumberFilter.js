(function(){

   'use strict'

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

   angular
      .module('demoApp')
      .filter("brDecimalNumber", brDecimalNumberFilter);

})();
