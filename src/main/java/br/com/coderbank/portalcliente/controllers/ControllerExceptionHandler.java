package br.com.coderbank.portalcliente.controllers;

import br.com.coderbank.portalcliente.dtos.response.ErrorResponseDTO;
import br.com.coderbank.portalcliente.exceptions.ClienteJaExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//Permite criar um componente global de tratamento de erros que pode ser usado por todos os controller
@ControllerAdvice
public class ControllerExceptionHandler {

    //    Filtro que intercepta todas as exceções do tipo ClienteJaExistenteException. Sempre que ocorrer uma
//    exceção do tipo ClienteJaExistenteException, o retorno será redirecionado para esse método.

    @ExceptionHandler({ClienteJaExistenteException.class})
//    Pegue o objeto que eu estou retornando e coloque-o diretamente no corpo da resposta HTTP.
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDTO conflict(final Throwable exception) {
        final var exceptionMessage = exception.getMessage();

        return new ErrorResponseDTO(exceptionMessage, System.currentTimeMillis());
    }
}