package com.hit.model;

public class WrongInput  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public WrongInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WrongInput(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public WrongInput(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public WrongInput(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public WrongInput(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "User input is not good";
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMessage();
	}
}
