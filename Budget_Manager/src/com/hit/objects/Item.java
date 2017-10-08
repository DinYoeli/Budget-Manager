package com.hit.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hit.model.WrongInput;

public class Item {
/***
 * Item parameters
 */
		private int id;
		private int userId;
		private String  item;
		private double price;
		private Date  date;
		private String category;
		public Item(){}
		
		/**
		 * 
		 * @param user_id = user item
		 * @param item = item name
		 * @param price = item price
		 * @param category = item category
		 * @throws WrongInput
		 */
		public Item(int user_id, String item, double price,String category) throws WrongInput {
			super();
			this.setItem(item);
			this.setPrice(price);
			this.setDate(date);
			this.setUserId(user_id);
			this.setCategory(category);
		}
		
		public Item(int user_id, String item, String price,String mode,String category) throws WrongInput {
			super();
			this.setItem(item);
			this.setPrice(price);
			this.priceMode(mode);
			this.setDate(date);
			this.setUserId(user_id);
			this.setCategory(category);
		}
		
		
		public void priceMode(String mode) {
			// TODO Auto-generated method stub
			if (mode.equals("off")) {
				this.price *=-1;
			}
			
		}

		public void setPrice(String price) throws WrongInput {
			// TODO Auto-generated method stub
			if (price.contains("[a-zA-Z]+")|| price.equals("")) {
				throw new WrongInput();
			}
			double value=0;
			try{
			 value = Double.parseDouble(price);
			}catch (NumberFormatException e) {
				throw new WrongInput();
			}
			this.price = value;
			
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) throws WrongInput {
			if (category==null || category.equals("")) {
				throw new WrongInput();
				
			}
			this.category = category;
		}

		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public String getItem() {
			return item;
		}
		public void setItem(String item) throws WrongInput {
			if (item==null || item.equals("")) {
				throw new WrongInput();
				
			}
			this.item = item;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			if(date==null)
			{
				SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
			
				try {
					this.date=dtf.parse(dtf.format(cal.getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			this.date=date;
			
			
		}



		@Override
		public String toString() {
			return "Item [id=" + id + ", user_id=" + userId + ", item=" + item + ", price=" + price + ", date=" + date
					+ "]";
		}

		public int getUserId() {
			return userId;
		}



		public void setUserId(int user_id) {
			this.userId = user_id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((category == null) ? 0 : category.hashCode());
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result + id;
			result = prime * result + ((item == null) ? 0 : item.hashCode());
			long temp;
			temp = Double.doubleToLongBits(price);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Item other = (Item) obj;
			if (category == null) {
				if (other.category != null)
					return false;
			} else if (!category.equals(other.category))
				return false;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (id != other.id)
				return false;
			if (item == null) {
				if (other.item != null)
					return false;
			} else if (!item.equals(other.item))
				return false;
			if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
				return false;
			if (userId != other.userId)
				return false;
			return true;
		}
		
		
		
		
}
