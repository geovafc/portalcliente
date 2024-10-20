package br.com.coderbank.portalcliente.controllers;

import br.com.coderbank.portalcliente.exceptions.ClienteJaExistenteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.HashMap;

//Permite criar um componente global de tratamento de erros que pode ser usado por todos os controller
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    //    Filtro que intercepta todas as exceções do tipo ClienteJaExistenteException. Sempre que ocorrer uma
//    exceção do tipo ClienteJaExistenteException, o retorno será redirecionado para esse método.

    @ExceptionHandler({ClienteJaExistenteException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail conflict(final Throwable exception) {
        final var exceptionMessage = exception.getMessage();

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exceptionMessage);
        problemDetail.setTitle("conflict");

        problemDetail.setType(URI.create("https://www.coderbank.com.br/fordevs/docs/erros/conflict"));

        log.error("m=conflict, ex= {}", exceptionMessage);
        return problemDetail;
    }

    // Trata exceções de validação de argumentos no corpo da requisição
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
//    CLIENTE ENVIOU DADOS INVÁLIDOS
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleValidation(final MethodArgumentNotValidException ex) {
        final var errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {

                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();

                    errors.put(fieldName, errorMessage);
                });

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errors.toString());
        problemDetail.setTitle("Data sent in the request is invalid");

// URL que pode ser usado para vincular a descrição do erro a uma documentação sobre como resolver o erro
        problemDetail.setType(URI.create("https://www.coderbank.com.br/fordevs/docs/erros/invalids-requests"));

        log.error("m=conflict, ex= {}", errors);

        return problemDetail;

    }
}