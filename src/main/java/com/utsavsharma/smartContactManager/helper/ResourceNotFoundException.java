package com.utsavsharma.smartContactManager.helper;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("User Not Found");
    }

  

}
