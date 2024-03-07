package api.finances.exception.customExceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Ocorreu um erro na requisição!");
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
