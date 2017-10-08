package com.hit.objects;

public class Category {
	/***
	 * Category parameters
	 */
	int id;
	private int userId;
	private String categoryName;

	@Override
	public String toString() {
		return "Category [user_id=" + userId + ", category_name=" + categoryName + "]";
	}

	public Category() {}
	
	/**
	 * 
	 * @param user_id = user that belong to this category
	 * @param categoryName = category name
	 */
	public Category(int user_id, String categoryName) {
		this.setUserId(user_id);
		this.setCategoryName(categoryName);
		this.setId(user_id);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + id;
		result = prime * result + userId;
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
		Category other = (Category) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (id != other.id)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
}
