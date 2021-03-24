package hu.tmx.customException;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String errorMessage) {
        super(errorMessage);
    }
}
