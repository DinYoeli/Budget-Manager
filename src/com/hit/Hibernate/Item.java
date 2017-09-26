package com.hit.Hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hit.Exception.WrongInput;

public class Item {

		private int id;
		private int user_id;
		private String  item;
		private double price;
		private Date  date;
		private String category;
		public Item(){}
		
		public Item(int user_id, String item, double price,String category) throws WrongInput {
			super();
			this.setItem(item);
			this.setPrice(price);
			this.setDate(date);
			this.setUser_id(user_id);
			this.setCategory(category);
		}
		
		public Item(int user_id, String item, String price,String mode,String category) throws WrongInput {
			super();
			this.setItem(item);
			this.setPrice(price);
			this.priceMode(mode);
			this.setDate(date);
			this.setUser_id(user_id);
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
			double value = Double.parseDouble(price); 
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
			return "Item [id=" + id + ", user_id=" + user_id + ", item=" + item + ", price=" + price + ", date=" + date
					+ "]";
		}

		public int getUser_id() {
			return user_id;
		}



		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
		
		
		
		
}
