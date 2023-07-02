package si.zpiz.sample.infrastructure.exceptions;

public class MediatorException extends Exception {
    public MediatorException() {
        super();
    }

    public MediatorException(String message) {
        super(message);
    }

    public MediatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
