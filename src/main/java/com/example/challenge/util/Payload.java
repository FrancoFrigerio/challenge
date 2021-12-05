package com.example.challenge.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class Payload {
    private final String message;
    private final HttpStatus HttpStatus;
    private final Date timestamp;
}
