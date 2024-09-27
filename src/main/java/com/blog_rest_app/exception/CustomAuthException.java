package com.blog_rest_app.exception;

public class CustomAuthException extends RuntimeException{
    public CustomAuthException(String message) {
        super(message);
    }
}
