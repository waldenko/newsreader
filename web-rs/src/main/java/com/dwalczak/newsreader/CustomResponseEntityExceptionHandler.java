package com.dwalczak.newsreader;

import com.dwalczak.newsreader.newsapi.NewsapiException;
import com.dwalczak.newsreader.rs.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NewsapiException.class)
    public @ResponseBody ResponseEntity<Error> handleNewsapiException(NewsapiException e, WebRequest request) {
        Error error = new Error().code("newsapi_connection_error").message("Błąd połączenia z newsapi.org");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity<Error> handleExceptionInternal(Exception e, WebRequest request) {
        Error error = new Error().code("server_error").message("Błąd serwera");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
