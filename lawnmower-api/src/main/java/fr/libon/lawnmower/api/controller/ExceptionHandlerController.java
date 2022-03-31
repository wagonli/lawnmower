package fr.libon.lawnmower.api.controller;

import fr.libon.lawnmower.api.controller.dto.ErrorMessageDto;
import fr.libon.lawnmower.api.controller.dto.ErrorMessageResponseBuilder;
import fr.libon.lawnmower.core.exception.LawnmowerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return (ResponseEntity) new ErrorMessageResponseBuilder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Wrong payload")
                .detail(errors.toString())
                .build();
    }

    @ExceptionHandler(LawnmowerException.class)
    public ResponseEntity<ErrorMessageDto> handleException(LawnmowerException exception) {
        return new ErrorMessageResponseBuilder()
                .status(HttpStatus.BAD_REQUEST)
                .title("LawnmowerException")
                .detail(exception.getMessage())
                .build();

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageDto> handleException(RuntimeException exception) {
        return new ErrorMessageResponseBuilder()
                .status(HttpStatus.BAD_REQUEST)
                .title("RuntimeException")
                .detail(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleException(Exception exception) {
        return new ErrorMessageResponseBuilder()
                .status(HttpStatus.BAD_REQUEST)
                .title("Exception")
                .detail(exception.getMessage())
                .build();
    }
}
