package com.projet.korector.exception;


import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ApiException extends RuntimeException {
    private final ResponseEntity<?> responseEntity;

    public ApiException(ResponseEntity<?> responseEntity) {
        this.responseEntity = responseEntity;
    }

    @Override
    public String getMessage() {
        return "API response entity: [" + responseEntity + "]";
    }
}