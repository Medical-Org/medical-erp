package com.example.platformerp.exceptions;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message){
        super(message);
    }
}
