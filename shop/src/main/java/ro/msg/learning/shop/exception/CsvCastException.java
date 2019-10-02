package ro.msg.learning.shop.exception;

public class CsvCastException extends RuntimeException {

    public CsvCastException(String errorMessage) {
        super(errorMessage);
    }
}
