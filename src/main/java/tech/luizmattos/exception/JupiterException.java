package tech.luizmattos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JupiterException extends Exception{
    public JupiterException() {
    }

    public JupiterException(String message) {
        super(message);
    }
}
