package ua.kay.online.store.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.kay.online.store.exception.PageNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<RestError> customHandleNotFound(Exception ex) {
        return new ResponseEntity<>(new RestError(HttpStatus.NOT_FOUND, ex), HttpStatus.NOT_FOUND);
    }
}
