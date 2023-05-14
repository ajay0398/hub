package com.hub.exceptions;

public class SpringHubException extends RuntimeException {
    public SpringHubException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringHubException(String exMessage) {
        super(exMessage);
    }
}
