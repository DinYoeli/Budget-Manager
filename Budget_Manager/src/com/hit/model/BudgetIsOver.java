package com.hit.model;

public class BudgetIsOver extends Exception{
 
	double budget;

	
	
	
	public BudgetIsOver(double budget) {
		super();
		this.budget = budget;
	}

	public BudgetIsOver(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BudgetIsOver(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BudgetIsOver(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BudgetIsOver(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "The amount of money that went out exceeds the budget";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "You spent more than your budget on "+ budget ;
	}
	
	
}
