package com.hit.objects;

import javax.persistence.criteria.CriteriaBuilder.In;

public enum Frequency {
	DAY(1),
	WEEK(7),
	MONTH(30),
	YEAR(365);

	private  Integer numOfDays;
	 Frequency(Integer numOfDays) { this.numOfDays = numOfDays; }
    public int getValue() { return numOfDays; }
    
}
