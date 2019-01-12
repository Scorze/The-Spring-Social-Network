package org.springcourse.project.springsocialnetwork.exception;

public class DuplicateUserException extends RuntimeException {

    private static final long serialVersionUID = -3173268277285704441L;

    public DuplicateUserException(String message) {
        super(message);
    }

    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserException(Throwable cause) {
        super(cause);
    }

}
