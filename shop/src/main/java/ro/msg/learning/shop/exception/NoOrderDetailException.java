package ro.msg.learning.shop.exception;

public class NoOrderDetailException extends RuntimeException {

    public NoOrderDetailException(String errorMessage) {
        super(errorMessage);
    }
}
