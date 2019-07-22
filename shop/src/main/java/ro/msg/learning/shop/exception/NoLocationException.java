package ro.msg.learning.shop.exception;


public class NoLocationException extends RuntimeException {

    public NoLocationException(String errorMessage) {
        super(errorMessage);
    }
}