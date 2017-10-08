package com.hit.model;

public class UserExistException  extends Exception{

	
	
	
	public UserExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "User already exists ";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMessage();
	}
}
