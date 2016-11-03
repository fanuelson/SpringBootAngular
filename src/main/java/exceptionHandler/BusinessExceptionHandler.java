package exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.foundation.exception.ValidacaoException;
import com.foundation.validacao.Validacoes;

public class BusinessExceptionHandler {

	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<?> businessExceptionHandler(HttpServletRequest req, Exception e) {
		if(e instanceof ValidacaoException) {
			ValidacaoException ve = (ValidacaoException) e;
			return new ResponseEntity<Validacoes>(ve.getValidacoes(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

}
