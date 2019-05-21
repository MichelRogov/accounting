package com.school.project.exception;

import javassist.NotFoundException;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
