package api.finances.exception;


import api.finances.exception.customExceptions.BadRequestException;
import api.finances.exception.customExceptions.NotFoundException;
import api.finances.exception.customExceptions.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExecption extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        List<ResponseError.FieldsValidation> validations = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach(error -> {

            String campo = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            validations.add(new ResponseError.FieldsValidation(campo, mensagem));

        });

        ResponseError campos = ResponseError
                .builder()
                .title("Dados inválidos")
                .message("Campos inválidos, verifique os campos")
                .code(HttpStatus.BAD_REQUEST.value())
                .date(LocalDateTime.now())
                .validations(validations)
                .build();

        return new ResponseEntity<>(campos, HttpStatus.BAD_REQUEST);
    }
}
