package com.example.ITTools.Region.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409 Conflict
public class RegionYaExisteException extends RuntimeException {
    public RegionYaExisteException(String message) {
        super(message);
    }
}