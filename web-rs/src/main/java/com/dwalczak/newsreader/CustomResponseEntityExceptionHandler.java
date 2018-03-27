package com.dwalczak.newsreader;

import com.dwalczak.newsreader.newsapi.NewsapiException;
import com.dwalczak.newsreader.rs.dto.Error;
import com.dwalczak.newsreader.rs.dto.FieldError;
import com.dwalczak.newsreader.service.NewsServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Iterator;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
@Slf4j
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NewsapiException.class)
    public @ResponseBody ResponseEntity<Error> handleNewsapiException(NewsapiException e, WebRequest request) {
        Error error = new Error().errorCode("newsapi_connection_error").message("Błąd połączenia z newsapi.org");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NewsServiceException.class)
    public @ResponseBody ResponseEntity<Error> handleNewsServiceException(NewsServiceException e, WebRequest request) {
        Error error = new Error().errorCode("newsservice_error").message("Błąd przetwarzania artykułów");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody ResponseEntity<Error> handleNewsServiceException(ConstraintViolationException e, WebRequest request) {
        Error error = new Error()
                .errorCode("validation_failed")
                .message("Błąd walidacji")
                .fields(e.getConstraintViolations().stream()
                        .map(constraintViolation -> new FieldError()
                                .field(getFieldName(constraintViolation.getPropertyPath()))
                                .message(constraintViolation.getMessage()))
                        .collect(Collectors.toList()));
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    private String getFieldName(Path path) {
        Iterator<Path.Node> iterator = path.iterator();
        Path.Node node = iterator.next();
        while (iterator.hasNext()) {
            node = iterator.next();
        }
        return node.getName();
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity<Error> handleExceptionInternal(Exception e, WebRequest request) {
        log.error("handle error", e);
        Error error = new Error().errorCode("server_error").message("Błąd serwera");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
