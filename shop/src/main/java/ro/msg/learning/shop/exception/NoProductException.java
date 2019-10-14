package ro.msg.learning.shop.exception;

public class NoProductException extends RuntimeException {

    public NoProductException(String errorMessage) {
        super(errorMessage);
    }
}

