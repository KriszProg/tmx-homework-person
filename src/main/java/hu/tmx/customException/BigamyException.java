package hu.tmx.customException;

public class BigamyException extends RuntimeException {
    public BigamyException(String errorMessage) {
        super(errorMessage);
    }
}
