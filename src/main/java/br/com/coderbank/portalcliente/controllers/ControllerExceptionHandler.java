package br.com.coderbank.portalcliente.controllers;

import br.com.coderbank.portalcliente.dtos.response.ErrorResponseDTO;
import br.com.coderbank.portalcliente.exceptions.ClienteJaExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

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

    // Trata exceções de validação de argumentos no corpo da requisição
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
//    CLIENTE ENVIOU DADOS INVÁLIDOS
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleValidation(final MethodArgumentNotValidException ex) {
        final var errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {

                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();

                    errors.put(fieldName, errorMessage);
                });

//        return ResponseEntity.badRequest().body(errors);
        return new ErrorResponseDTO(errors.toString(), System.currentTimeMillis());

    }
}