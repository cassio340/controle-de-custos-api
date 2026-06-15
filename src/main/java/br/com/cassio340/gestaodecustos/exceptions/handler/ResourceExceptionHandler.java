package br.com.cassio340.gestaodecustos.exceptions.handler;

import br.com.cassio340.gestaodecustos.exceptions.custom.BadRequestException;
import br.com.cassio340.gestaodecustos.exceptions.custom.DataBaseException;
import br.com.cassio340.gestaodecustos.exceptions.custom.ResourceNotFoundException;
import br.com.cassio340.gestaodecustos.exceptions.response.StandardError;
import br.com.cassio340.gestaodecustos.exceptions.response.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e , HttpServletRequest request){

        String err = "Resource Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), status.value(),
                err,e.getMessage(),request.getRequestURI());

        return  ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> methodArgumentTypeMismatch (MethodArgumentTypeMismatchException e ,
                                                                     HttpServletRequest request){
        String err = "Bad Request";
        String message = "Invalid parameter " + e.getName();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError standardError = new StandardError(Instant.now(),status.value(),
                err,message, request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler (DataBaseException.class)
    public ResponseEntity<StandardError> dataBaseException (DataBaseException e,HttpServletRequest request){

        String err = "Data base error";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError standardError = new StandardError(Instant.now(),status.value(),err,
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> httpRequestMethodNotSupported (HttpRequestMethodNotSupportedException e ,
                                                                        HttpServletRequest request){
        String err = "Method not allowed";
        String message = "Method '" + e.getMethod() + "' is not supported for this endpoint";
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;


        StandardError standardError = new StandardError(Instant.now(),status.value(),err,
                message, request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequest (BadRequestException e , HttpServletRequest request){

        String err = "Bad Request";
        HttpStatus status =HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(),status.value(),err,
                e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e,
                                                      HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ValidationError error = new ValidationError(
                Instant.now(),
                status.value(),
                "Validation Error",
                "Invalid fields",
                request.getRequestURI()
        );

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(error);
    }

}
