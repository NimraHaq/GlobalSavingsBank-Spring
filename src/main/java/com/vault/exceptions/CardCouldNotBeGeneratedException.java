package com.vault.exceptions;

public class CardCouldNotBeGeneratedException extends RuntimeException{
    public CardCouldNotBeGeneratedException() {
    }

    public CardCouldNotBeGeneratedException(String message) {
        super(message);
    }

    public CardCouldNotBeGeneratedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardCouldNotBeGeneratedException(Throwable cause) {
        super(cause);
    }
}
