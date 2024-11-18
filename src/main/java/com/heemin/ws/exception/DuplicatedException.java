package com.heemin.ws.exception;

public class DuplicatedException extends RuntimeException {

	public DuplicatedException(String msg) {
		super(msg);
	}
}
