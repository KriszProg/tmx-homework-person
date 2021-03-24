package hu.tmx.customException;

public class SameSexException extends RuntimeException {
    public SameSexException(String errorMessage) {
        super(errorMessage);
    }
}
