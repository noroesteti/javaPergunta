package com.example.javaPergunta.domain.exceptions;

public class IllegalArgumentException extends RuntimeException{
    public IllegalArgumentException(String message){super(message);}

    public IllegalArgumentException(String message, Throwable cause){super(message, cause);}
}
