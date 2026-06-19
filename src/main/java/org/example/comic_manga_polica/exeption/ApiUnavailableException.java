package org.example.comic_manga_polica.exeption;

public class ApiUnavailableException extends RuntimeException {
    public ApiUnavailableException(String message) {
        
        super(message);
    }
}
