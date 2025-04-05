package com.railway.user_service.exception;

public class FieldAlreadyExistException extends RuntimeException {
    public FieldAlreadyExistException(String message) {
        super(message);
    }
}
