package br.com.gc.api.error;


import br.com.gc.api.constants.ValidateConstants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject> errors) {
        return new ErrorResponse(ValidateConstants.VALIDATE_DEFAULT_ERROR, status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }

    public static ErrorResponse getConstraintErrors(DataIntegrityViolationException ex) {
        List<ErrorObject> listError = new ArrayList<>();
        if(Objects.requireNonNull(ex.getRootCause()).getMessage().contains("PRIMARY KEY") && (ex.getRootCause().getMessage().contains("duplicate key"))){
            listError.add(new ErrorObject(ValidateConstants.VALIDATE_CONSTRAINT_ERROR, null, null));
        }else{
            listError.add(new ErrorObject(ValidateConstants.VALIDATE_DEFAULT_ERROR, null, null));
        }
        return new ErrorResponse(ValidateConstants.VALIDATE_DEFAULT_ERROR, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getRootCause().getMessage(), listError);
    }

    public static ErrorResponse getExceptionsErrors(Exception ex) {
        List<ErrorObject> listError = new ArrayList<>();
        listError.add(new ErrorObject(ex.getMessage(), null, null));
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),null, listError);
    }
}