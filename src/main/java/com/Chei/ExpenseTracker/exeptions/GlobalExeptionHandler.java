package com.Chei.ExpenseTracker.exeptions;

import com.Chei.ExpenseTracker.Entity.ErrorObject;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExeptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<ErrorObject>handleExceptionNotFound(ResourceNotFoundExeption ex, WebRequest request){
    ErrorObject errorObject= new ErrorObject();
    errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
errorObject.setMessage(ex.getMessage());
errorObject.setTimestamp(new Date());
return new  ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);
}
@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject>handleMethodTypeMismatch(MethodArgumentTypeMismatchException ex,WebRequest request){
    ErrorObject errorObject= new ErrorObject();
    errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new  ResponseEntity<>(errorObject,HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralExeption(Exception ex,WebRequest request){
    ErrorObject errorObject= new ErrorObject();
    errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorObject.setMessage(ex.getMessage());
    errorObject.setTimestamp(new Date());
    return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
}

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> body= new HashMap<>();
        body.put("timestamp",new Date());
        body.put("status",HttpStatus.BAD_REQUEST.value());
        List<String>errors=ex.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
        body.put("errors",errors);
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ItemAlreadyExistsExeption.class)
    public ResponseEntity<ErrorObject> handleItemAlreadyExistExeption(ItemAlreadyExistsExeption ex,WebRequest request){
        ErrorObject errorObject= new ErrorObject();
        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new  ResponseEntity<ErrorObject>(errorObject,HttpStatus.CONFLICT);
    }
}
