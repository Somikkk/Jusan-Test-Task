package kz.islam.jusan.exceptions;

public class ApiUnavailableException extends RuntimeException{
    public ApiUnavailableException(String message) {
        super(message);
    }
}
