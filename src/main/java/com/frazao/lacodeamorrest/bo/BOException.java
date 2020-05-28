package com.frazao.lacodeamorrest.bo;

public class BOException extends Exception {

	private static final long serialVersionUID = 1L;

	public BOException() {
		super();
	}

	public BOException(final String message) {
		super(message);
	}
	
	public BOException(Throwable t) {
		super (t);
	}
	
	public BOException(String message, Throwable t) {
		super (message, t);
	}

}
