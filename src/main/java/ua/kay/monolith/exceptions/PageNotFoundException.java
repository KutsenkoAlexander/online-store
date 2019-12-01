package ua.kay.monolith.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Page Not Found", code = HttpStatus.BAD_REQUEST)
public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(String message, String page) {
        super(message + " " + page);
    }

    public PageNotFoundException(String message, Long id) {
        super(message + " " + id);
    }
}
