package edu.eci.cvds.tdd.library.exceptions;

/**
 * Custom exception for invalid user operations.
 */
public class InvalidUserException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Evita advertencias de serialización

    public InvalidUserException(String message) {
        super(message);
    }
}
