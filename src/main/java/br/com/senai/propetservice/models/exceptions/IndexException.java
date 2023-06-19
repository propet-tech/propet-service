package br.com.senai.propetservice.models.exceptions;

public class IndexException extends RuntimeException {

    public IndexException() {
        super();
    }

    public IndexException(
            String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexException(String message) {
        super(message);
    }

    public IndexException(Throwable cause) {
        super(cause);
    }
}
