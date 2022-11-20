package com.kurtcan.javacore.utilities.message.exceptions;

public class MessageSenderException extends RuntimeException{
    public MessageSenderException() {
        super("Message sender exception");
    }
    public MessageSenderException(String message) {
        super(message);
    }
}