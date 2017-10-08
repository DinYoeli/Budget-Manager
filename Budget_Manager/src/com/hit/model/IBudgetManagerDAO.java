package com.hit.model;

import java.util.Date;
import java.util.List;

import com.hit.objects.Category;
import com.hit.objects.Item;
import com.hit.objects.User;

public interface IBudgetManagerDAO {
	
	//Print the existing users in datebase
	public  void GetDataUsers() throws SaveDataFailed; 
	
	//Add a new user acount to the datebase
	public  User AddUser(String name, String password, String sBudget, String frequency) throws WrongInput, UserExistException, SaveDataFailed;
	
	//Check if the user name already exist in database
    public boolean User_exist(String name) throws SaveDataFailed;
    
    //Get user from the database , the username is the name that the method get
    public User GetUser(String name) throws SaveDataFailed;
    
    //Get all the users from the db
    public List<User> GetUsers() throws SaveDataFailed;
    
    //Delete requested user from the database
    public void Delete_User(User u) throws SaveDataFailed;
    
    //Update the user badget in the database
    public void Update_budget(User u, String sBudget) throws WrongInput, SaveDataFailed;
    
    //Update the user password in the database
    public void Update_Password(User u, String password) throws SaveDataFailed, WrongInput;
    
    //Update the user frequency in the database
    public void Update_frequency(User u,String frequency) throws SaveDataFailed;
    
    //Add a new item to the database
    public void Add_Item(User user, String item, String sValue,String mode, String category,List<Item> items) throws WrongInput, BudgetIsOver, SaveDataFailed;
    
    //Update the item name in the database
    public void Update_Item_Name(Item i, String name) throws SaveDataFailed, WrongInput;
    
    //Update the item price in the database
    public void Update_Item_Price(Item i, String price) throws SaveDataFailed, WrongInput;
   
    //Delete the requested item from the database
    public void Delete_Item(Item i) throws SaveDataFailed;
    
    //Get requested item from the database, the id variable is the item id in the itemTable database
    public Item Get_Item(String id) throws SaveDataFailed, WrongInput;
    
    //Get a list of the user items in the database with the dates that he ask for in the frequency
    public List<Item> Get_User_Items_by_Date(User u) throws SaveDataFailed;
    
    //Get a list of the user items in the database 
    public List<Item> Get_User_Items(User u) throws SaveDataFailed;
    
    //Add a new Category to a user in the database
    public void Add_Category(User u,String name) throws SaveDataFailed;
    
    //Delete requested Category from the CategoryTable in the database
    public void Delete_Category(Category c) throws SaveDataFailed;
    
    //Update the category name in the CategoryTable
    public void Update_Category_name(Category c,String name,User user) throws SaveDataFailed, WrongInput;
    
    //Get a list of the user Categories that exsit in the database
    public List<Category> Get_Uset_Categories(User u) throws SaveDataFailed;
    
    //Get a category of user that the name is the category name 
    public Category Get_User_Category(User u,String name) throws SaveDataFailed;
    
    //Check if the category name already exist in the database
    public boolean Category_Exist(User u, String name) throws SaveDataFailed;
    
    //Recive one category of user
    public Category Get_User_Category(User u, int id) throws SaveDataFailed;
    
    //Update the category name 
    public void Update_Item_Category(Item i, String Category) throws SaveDataFailed, WrongInput;
    
    
    
    
    
    
}
