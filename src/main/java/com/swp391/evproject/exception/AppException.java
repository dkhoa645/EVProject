package com.swp391.evproject.exception;

public class AppException extends RuntimeException {
    public AppException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }

    private ErrorCode error;

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }
}
