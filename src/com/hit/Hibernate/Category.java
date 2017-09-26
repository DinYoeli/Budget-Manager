package com.hit.Hibernate;

public class Category {
	
	int id;
	private int user_id;
	private String category_name;

	@Override
	public String toString() {
		return "Category [user_id=" + user_id + ", category_name=" + category_name + "]";
	}

	public Category() {}
	
	public Category(int user_id, String category_name) {
		this.setUser_id(user_id);
		this.setCategory_name(category_name);
		this.setId(user_id);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
}
