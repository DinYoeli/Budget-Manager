package com.hit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.hit.model.BudgetIsOver;
import com.hit.model.HibernateBudgetManagerDAO;
import com.hit.model.SaveDataFailed;
import com.hit.model.UserExistException;
import com.hit.model.WrongInput;
import com.hit.objects.Item;
import com.hit.objects.User;

import junit.framework.Assert;

public class ModelTest {

	@Test
	public void UserTest() throws SaveDataFailed, WrongInput, UserExistException {
		
		HibernateBudgetManagerDAO budgetManagerDAO = HibernateBudgetManagerDAO.getInstance();
	
		User user;
			if (budgetManagerDAO.User_exist("Test")) {
				
				user = budgetManagerDAO.GetUser("Test");
				budgetManagerDAO.Delete_User(user);
			}
		
			
		
		
		
			Assert.assertEquals(false, budgetManagerDAO.User_exist("Test"));
	
			budgetManagerDAO.AddUser("Test", "123", "1000", "Day");
		
		
			Assert.assertEquals(true, budgetManagerDAO.User_exist("Test"));
		
		
		
		user = budgetManagerDAO.GetUser("Test");
		
		budgetManagerDAO.Update_budget(user, "200");
		
		Assert.assertEquals(200, user.getBudget());
		
		
		budgetManagerDAO.Update_frequency(user, "Week");
		
		user = budgetManagerDAO.GetUser("Test");
		
		Assert.assertEquals(7, user.getFrequency());
		
		budgetManagerDAO.Update_Password(user, "1234");
		
		Assert.assertEquals("1234", user.getPassword());
		
		budgetManagerDAO.Delete_User(user);
		
		Assert.assertEquals(false, budgetManagerDAO.User_exist("Test"));
	}
	
	@Test
	public void ItemTest() throws WrongInput, UserExistException, SaveDataFailed, BudgetIsOver {
		HibernateBudgetManagerDAO budgetManagerDAO = HibernateBudgetManagerDAO.getInstance();
		
		User user;
		if (budgetManagerDAO.User_exist("TestItem")) {
			
			user = budgetManagerDAO.GetUser("TestItem");
			budgetManagerDAO.Delete_User(user);
		}
	
		
		budgetManagerDAO.AddUser("TestItem", "123", "1000", "1");
		
		
		 user = budgetManagerDAO.GetUser("TestItem");
		
		
		List<Item> items = budgetManagerDAO.Get_User_Items(user);
		
		
		Assert.assertEquals(0, items.size());
		
		
		budgetManagerDAO.Add_Item(user, "ItemTest", "100", "inc", "Category", items);
		
		items = budgetManagerDAO.Get_User_Items(user);
		
		
		Assert.assertEquals(1, items.size());
		Item item =items.get(0);
		budgetManagerDAO.Update_Item_Category(item, "Change category");
		
		items = budgetManagerDAO.Get_User_Items(user);
		
		item =items.get(0);
		
		Assert.assertEquals(item.getCategory(), "Change category");
		
		budgetManagerDAO.Update_Item_Name(item, "Item");
		
		items = budgetManagerDAO.Get_User_Items(user);
		
		item =items.get(0);
		
		Assert.assertEquals(item.getItem(), "Item");
		
		budgetManagerDAO.Update_Item_Price(item, "1000");
		
		items = budgetManagerDAO.Get_User_Items(user);
		
		item =items.get(0);
		
		Assert.assertEquals(item.getPrice(), 1000.0);
		
		budgetManagerDAO.Delete_Item(item);
		
		items = budgetManagerDAO.Get_User_Items(user);
		
		
		Assert.assertEquals(0, items.size());
		
		budgetManagerDAO.Delete_User(user);
		
		
		Assert.assertEquals(false, budgetManagerDAO.User_exist("TestItem"));
	}

}
