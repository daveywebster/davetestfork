package com.dave.techtest.exception;

public class DuplicateTeamException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateTeamException() {
        super();
    }

    public DuplicateTeamException(String message) {
        super(message);
    }

}
