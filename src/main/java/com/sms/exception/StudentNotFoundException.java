package com.sms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested Student record is not found.
 * Author: Swaraj Surendra Pakhale (Roll No: 172)
 * College: Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }
}
