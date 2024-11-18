package com.heemin.ws.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("권한 없음");
    }
}