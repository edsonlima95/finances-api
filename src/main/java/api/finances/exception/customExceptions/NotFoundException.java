package api.finances.exception.customExceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Long id) {
        super(String.format("Recurso de id %d não pode ser encontrado!", id));
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
