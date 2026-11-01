package com.inventory.frontend.backend;

/** The reporting backend could not be reached or rejected the request. */
public class BackendUnavailableException extends RuntimeException {

    public BackendUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
