package com.demo;

class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	UserNotFoundException(int id) {
		super("Could not find username " + id);
	}
}