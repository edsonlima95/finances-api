package api.finances.exception;


import api.finances.exception.customExceptions.BadRequestException;
import api.finances.exception.customExceptions.NotFoundException;
import api.finances.exception.customExceptions.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.LocalTime;

@ControllerAdvice
public class GlobalExecption {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> Exception() {

        ResponseError responseError = ResponseError.builder()
                .title("Erro interno na aplicação")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Ocorreu algum erro na aplicação!")
                .date(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {

        ResponseError responseError = ResponseError.builder()
                .title("Erro no recurso")
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .date(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {

        ResponseError responseError = ResponseError.builder()
                .title("Erro na requisição")
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .date(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

}
