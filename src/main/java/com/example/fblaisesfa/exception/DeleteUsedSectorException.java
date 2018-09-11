package com.example.fblaisesfa.exception;

public class DeleteUsedSectorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteUsedSectorException() {
		super("The sector you want to delete is actually used. you cannot delete it");
	}

}
