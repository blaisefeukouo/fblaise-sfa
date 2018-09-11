package com.example.fblaisesfa.exception;

public class GenerateOutOfBoundOperationNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenerateOutOfBoundOperationNumberException() {
		super("Sorry, you can no more generate any command or sale number");
	}

}
