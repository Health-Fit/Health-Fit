package com.heemin.ws.model.dto;

import org.springframework.http.ResponseEntity;

public class Response {
    Object data;
    int status;

    public Response() {
    }

    public Response(Object data, int status) {
        this.data = data;
        this.status = status;
    }

    public ResponseEntity<?> getResponse() {
        return ResponseEntity.status(this.status).body(data);
    }
}
