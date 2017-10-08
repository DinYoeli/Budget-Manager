package com.hit.objects;

import com.hit.model.WrongInput;

public class User implements java.io.Serializable{
    /**
	 * User parameters 
	 */
	private static final long serialVersionUID = 1L;
	private  int id;
    private String name;
    private  String password;
    private int budget;
	private int frequency;


    public User() {
		super();
	}
    
    /**
     * 
     * @param name = user name
     * @param password = user password
     * @param budget = user budget
     * @param frequency = frequency of the budget
     * @throws WrongInput
     */
	public User(String name, String password, String budget,String frequency) throws WrongInput {
		super();
		this.setName(name);;
		this.setPassword(password);
		this.setBudget(budget);
		this.setFrequency(frequency);
	}
	
	public User(String name, String password, int budget,int frequency) throws WrongInput {
		super();
		this.setName(name);;
		this.setPassword(password);
		this.setBudget(budget);
		this.setFrequency(frequency);
	}
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public void setFrequency(String frequency) {
		Frequency budgetPer;
		
		//	Checks for which period you chose your budget
		switch (frequency ) {
		case "Day":
			budgetPer = Frequency.DAY;
			break;
		case "Month":
			budgetPer = Frequency.MONTH;
			break;
		case "Year":
			budgetPer = Frequency.YEAR;
			break;
		case "Week":
			budgetPer = Frequency.WEEK;
			break;

		default:
			budgetPer = Frequency.DAY;
			break;
		}
		this.frequency= budgetPer.getValue();
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}
	public void setBudget(String sBudget) throws WrongInput {
		if (sBudget.contains("[a-zA-Z]+")|| sBudget.equals("")) {
			throw new WrongInput();
		}
		int budget = Integer.parseInt(sBudget);
		this.budget = budget;
	}

	public String getPassword() {
        return password;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) throws WrongInput {
    	if (name==null ) {
			throw new WrongInput();
		}
        this.name = name;
    }

    public void setPassword(String password) throws WrongInput {
    	if ( password.equals("")) {
			throw new WrongInput();
		}
        this.password = password;
    }

	@Override
	public String toString() {
		return "User [id=" +id +"name=" + name + ", password=" + password + ", budget=" + budget + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + budget;
		result = prime * result + frequency;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (budget != other.budget)
			return false;
		if (frequency != other.frequency)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


 
}
