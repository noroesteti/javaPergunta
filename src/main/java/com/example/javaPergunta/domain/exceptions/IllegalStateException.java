package com.example.javaPergunta.domain.exceptions;

public class IllegalStateException extends RuntimeException{
    public IllegalStateException(String message){super(message);}

    public IllegalStateException(String message, Throwable cause){super(message, cause); }
}
