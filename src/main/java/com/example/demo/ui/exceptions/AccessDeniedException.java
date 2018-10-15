package com.example.demo.ui.exceptions;

public class AccessDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

  public AccessDeniedException() {
	}

	public AccessDeniedException(String message) {
		super(message);
	}
}
