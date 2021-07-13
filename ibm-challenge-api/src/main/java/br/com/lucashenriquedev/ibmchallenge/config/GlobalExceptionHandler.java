package br.com.lucashenriquedev.ibmchallenge.config;

import br.com.lucashenriquedev.ibmchallenge.commons.exceptions.InvalidArgumentException;
import br.com.lucashenriquedev.ibmchallenge.commons.exceptions.ResourceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ResourceNotFound.class})
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFound ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponseError(ex));
    }

    @ExceptionHandler({InvalidArgumentException.class})
    public ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(buildResponseError(ex));
    }

    private Map<String, String> buildResponseError(Exception e) {
        return Collections.singletonMap("error", e.getMessage());
    }

}
