package com.hit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hit.model.BudgetIsOver;
import com.hit.model.HibernateBudgetManagerDAO;
import com.hit.model.SaveDataFailed;
import com.hit.model.UserExistException;
import com.hit.model.WrongInput;
import com.hit.objects.Category;
import com.hit.objects.Frequency;
import com.hit.objects.Item;
import com.hit.objects.User;



/**
 * Servlet implementation class Router
 */
@WebServlet("/Router")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HibernateBudgetManagerDAO hibernateBudgetManagerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Router() {
        super();
        hibernateBudgetManagerDAO = HibernateBudgetManagerDAO.getInstance();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String destination = "/MainPage.jsp";
	   
			 String export = request.getParameter("sendTo");
			 
			 log(" send to equal " + export);
	// 		 export is parameter that   tell me which page I should go
			 if (export != null) {
	// 		Check that it is not null
				 if(export.equals("New_User")) {
					 destination = "/NewUserPage.jsp";
				 }
				 else  if(export.equals("Login")) {
					 destination = "/MainPage.jsp";
				 }
				 else  if(export.equals("Setup")) {
					 destination = "/SetupPage.jsp";
					
				 }
				 else  if(export.equals("Main")) {
					 geUserFromSession(request);
					 geItemsFromSession(request);
					 destination = "/UserPage.jsp";		
				 }
				 else  if(export.equals("Transaction")) {
					 geCategoriesFromSession(request);
					 destination = "/TransactionPage.jsp";		
				 }
				 else  if(export.equals("ChangeBudget")) {
					 geUserFromSession(request);
					 destination = "/UpdateUserDetails.jsp";		
				 }
				 else  if(export.equals("AddType")) {
					 geUserFromSession(request);
					 geCategoriesFromSession(request);
					 destination = "/UpdateCategories.jsp";		
				 }
				 else  if(export.equals("chageItem")) {
					 geUserFromSession(request);
					 geItemsFromSession(request);
					 geCategoriesFromSession(request);
					 destination = "/ChangeItemPage.jsp";		
				 }
				 //chageItem
				 else  if(export.equals("chart")) {
					 geCategoriesFromSession(request);
					 geItemsFromSession(request);
					 geUserFromSession(request);
					
					 
					 destination = "/ChartPage.jsp";		
				 }
				 else if (export.equals("delete")) {
					 
						if (request.getParameter("deleteType").equals("item")) {
							try {
								destination = deleteGet(request, response);
							} catch (SaveDataFailed e) {
								// TODO Auto-generated catch block
								log(e.getMessage());
							} catch (WrongInput e) {
								// TODO Auto-generated catch block
								log(e.getMessage());
							}	
						} else
							try {
								destination =   deleteUser(request, response);
							} catch (SaveDataFailed e) {
								// TODO Auto-generated catch block
								log(e.getMessage());
							}	
				}
				 else if (export.equals("LogOut")) {
					 
						 request.getSession().invalidate();
						 destination = "/MainPage.jsp";
						
				}
			}
			 else {//The export parameter was null
				 Cookie cookie = null;//
				 Cookie[] cookies = null;
				 cookies = request.getCookies();//Recive the cookies
				 String username ="Username";//Look for cookie with the name Username

				 // Checks the name of the customer by cookies
				 if(cookies != null){
				 	for (int i = 0; i < cookies.length; i++) {
				    	 cookie = cookies[i];
				    	 if (cookie.getName().equals("username")) {//There's already user name in the cookies , so use this user for signing in
				     		username = cookie.getValue();
				     	
				     		try {
								if (hibernateBudgetManagerDAO.User_exist(username)) {
									User user = hibernateBudgetManagerDAO.GetUser(username);
								
									
									List<Item> listItem = hibernateBudgetManagerDAO.Get_User_Items_by_Date(user);
									
									// It takes from DB all transactions of the user
									
									request.setAttribute("Items", listItem);
									request.setAttribute("user", user);		

									List<Category> categories = hibernateBudgetManagerDAO.Get_Uset_Categories(user);
									//Takes from the database all the transactions of the user
									//And adds to the session  user details, transactions, categories
									request.getSession().setAttribute("user", user);
									request.getSession().setAttribute("Items", listItem);
									request.getSession().setAttribute("categories", categories);
								
									destination = "/UserPage.jsp";
									
									
								}
							} catch (SaveDataFailed e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				     		
				     		
				     	}
				 	}
				 }

				 
			 }
			  log("Do get to " + destination);
//	Takes the user to the desired page
		     RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		//     response.sendRedirect(request.getContextPath() +destination);
		     rd.forward(request, response);
		  
			 
	}
	private String deleteUser(HttpServletRequest request, HttpServletResponse response) throws SaveDataFailed {
		String userName = request.getParameter("userName");
		
		User user = hibernateBudgetManagerDAO.GetUser(userName);
		hibernateBudgetManagerDAO.Delete_User(user);

		List<User> listUsers = hibernateBudgetManagerDAO.GetUsers();
		request.setAttribute("Users", listUsers);
		request.getSession().setAttribute("users", listUsers);//Add to the session the list of users
		return  "/AdminPage.jsp";
	}

	private String deleteGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SaveDataFailed, WrongInput {
	
		// get the  item id
		String itemId= request.getParameter("itemId");
		
		// get the  item 
		Item i = hibernateBudgetManagerDAO.Get_Item(itemId);
		// Delete item
		hibernateBudgetManagerDAO.Delete_Item(i);
		//Obtains the user's information
		 User user = (User) request.getSession().getAttribute("user");	
			// set the new items 
		 List<Item> listItem =  hibernateBudgetManagerDAO.Get_User_Items_by_Date(user);
		 
		 // Adds the customer's recent transactions data to the attribute and to the session
		request.getSession().setAttribute("Items", listItem);
		 request.setAttribute("Items", listItem);
		 
		 //  Adds user data to the attribute
		 request.setAttribute("user", user);
		return "/UserPage.jsp";
	}
  /***
   * 
   * @Recive from the session the list of items 
   */
	private void geItemsFromSession(HttpServletRequest request) {
		List<Item> listItem =  (List<Item>) request.getSession().getAttribute("Items"); 		
		 request.setAttribute("Items", listItem);		
	}
	/***
	 * Recive from the session the user
	 * @param request
	 */
	private void geUserFromSession(HttpServletRequest request) {
		 User user = (User) request.getSession().getAttribute("user");	
		 request.setAttribute("user", user);
	}
	/***
	 * Recive from the session the list of categories
	 * @param request
	 */
	private void geCategoriesFromSession(HttpServletRequest request) {
		 List<Category>  categories=  (List<Category>) request.getSession().getAttribute("categories");	
		 request.setAttribute("categories", categories);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Here I handle all the forms
		String export =(String) request.getParameter("page");
		
		 log(" send to equal " + export);
//		 export is parameter    to tell me which page I should go
		 String destination = "/MainPage.jsp";	
		 if (export != null) {
				// 		Check that it is not null
			 if(export.equals("Login")) {
				 //	The user tries to make Login to his user
				 //I switch to a function that handles the request and returns the target to which the client is moving
				 try {
					destination = MainPage(request, response);
				} catch (SaveDataFailed e) {
					log(e.getMessage());
					
				}
			 }
			 else  if(export.equals("NewUser")) {
//					The user tries to make New User 
 //					I switch to a function that handles the request and returns the target to which the client is moving
				 try {
					destination = NewUserPage(request, response);
				} catch (SaveDataFailed e) {
					log(e.getMessage());
					
				}
			 }
			 else  if(export.equals("Setup")) {
				 destination = "/SetupPage.jsp";
				
			 }
			 else  if(export.equals("Main")) {
				 destination = "/UserPage.jsp";		
			 }
			 else  if(export.equals("Transaction")) {
//					The user tries to make transaction 
//					I switch to a function that handles the request and returns the target to which the client is moving
				 try {
					destination = TransactionnPage(request, response);
				} catch (SaveDataFailed e) {
					// TODO Auto-generated catch block
					log(e.getMessage());
				}	
			 }
			 else  if(export.equals("ChangeBudget")) {

//					The user tries to make change budget 
//					I switch to a function that handles the request and returns the target to which the client is moving
				 try {
					destination =changeBudgetForm(request, response);
				} catch (SaveDataFailed e) {
					log(e.getMessage());
					
				}
			 }
			 else  if(export.equals("AddType")) {
				 try {
					destination = AddTypePage(request, response);
				} catch (SaveDataFailed | WrongInput e) {
					log(e.getMessage());
					
				}		
			 }
			 else  if(export.equals("changeItem")) {
				 try {
					destination = changeItemPost(request, response);
				} catch (SaveDataFailed | WrongInput e) {
					// TODO Auto-generated catch block
					log(e.getMessage());
					
				}
			 }
		
		}
		  log("Do get to " + destination);

		 RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
	     rd.forward(request, response);
	}
	
	/***
	 * Update  the item values
	 */
	private String changeItemPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SaveDataFailed, WrongInput{
		// TODO Auto-generated method stub
		String itemPrice = request.getParameter("price");
		String itemId = request.getParameter("item");
		String itemName = request.getParameter("itemName");
		String  category = request.getParameter("categories");

		
		
		User user = (User) request.getSession().getAttribute("user");
		
		Item item = hibernateBudgetManagerDAO.Get_Item(itemId);
		
		hibernateBudgetManagerDAO.Update_Item_Price(item, itemPrice);
		hibernateBudgetManagerDAO.Update_Item_Name(item, itemName);
		hibernateBudgetManagerDAO.Update_Item_Category(item, category);
		
		
		
		geUserFromSession(request);
		List<Item> listItem = hibernateBudgetManagerDAO.Get_User_Items_by_Date(user);
		
		request.getSession().setAttribute("Items", listItem);
		
		 request.setAttribute("Items", listItem); 
		
		return "/UserPage.jsp";		
	}

	/***
	 *  Add a new Category name , Update existing category name
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SaveDataFailed
	 * @throws WrongInput
	 */
	private String AddTypePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SaveDataFailed, WrongInput {
		
		String Type = request.getParameter("Type");
		//Obtains the user's information
		String update = request.getParameter("Update");
	
			
		
		
		
		User user = (User) request.getSession().getAttribute("user");	
		
		if(update!=null)
		{
			int id = Integer.parseInt(update);
			Category c= hibernateBudgetManagerDAO.Get_User_Category(user, id);
			hibernateBudgetManagerDAO.Update_Category_name(c, Type,user);
			List<Item> items = hibernateBudgetManagerDAO.Get_User_Items_by_Date(user);
			request.getSession().setAttribute("Items", items);// changing the categories name in the session ( items category thats was change )
			
			
			
		}
		else{	
		// Add category 
		hibernateBudgetManagerDAO.Add_Category(user, Type);
		}
		// Set attribute of the  user
		request.setAttribute("user", user);
		
		// Add to the session the new categories
		List<Category> categories = hibernateBudgetManagerDAO.Get_Uset_Categories(user);
		request.getSession().setAttribute("categories", categories);
		geItemsFromSession(request);
		return  "/UserPage.jsp";
	}
	
	private String MainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SaveDataFailed {
		
		String userName = request.getParameter("user");
		String password = request.getParameter("passw");
		User user = hibernateBudgetManagerDAO.GetUser(userName);
		//Check that the user exists in the system
		if (user !=null) {
			 if (user.getPassword().equals(password)) {		
				 
				 //Check that the password a user entered is correct
				List<Item> listItem = hibernateBudgetManagerDAO.Get_User_Items_by_Date(user);
				
				// It takes from DB all transactions of the user
				
				request.setAttribute("Items", listItem);
				request.setAttribute("user", user);		
				Cookie ck = new Cookie("username", request.getParameter("user"));
				//set max age for cookie
				ck.setMaxAge(600);
				response.addCookie(ck);
				List<Category> categories = hibernateBudgetManagerDAO.Get_Uset_Categories(user);
				//Takes from the database all the transactions of the user
				//And adds to the session  user details, transactions, categories
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("Items", listItem);
				request.getSession().setAttribute("categories", categories);
				
				return "/UserPage.jsp";
			}
		}
		// check  for Adminstrator
		if (userName!=null && userName.equals("Adminstrator")) {
			if (password!=null && password.equals("123")) {
				// get all the users
				List<User> listUsers = hibernateBudgetManagerDAO.GetUsers();
				request.setAttribute("Users", listUsers);
				request.getSession().setAttribute("users", listUsers);
				return  "/AdminPage.jsp";
			}
			
		}
		request.setAttribute("Message", "Wrong input");
		return  "/MainPage.jsp";
		
	
	}
private String NewUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SaveDataFailed {
		
		// Takes all the details of the user 
		String userName = request.getParameter("user");
		String password = request.getParameter("passw");
		String budgetString = request.getParameter("Budget");
		String radio = request.getParameter("radio");
		
	
				
		//Creating a new user
		User user;
		try {
			user = hibernateBudgetManagerDAO.AddUser(userName, password, budgetString, radio);
		} catch (WrongInput e) {
			// TODO Auto-generated catch block
			log(e.getMessage());
			request.setAttribute("Message", e.getMessage());
			return  "/NewUserPage.jsp";
		}
		catch (UserExistException e) {
			// TODO Auto-generated catch block
			log(e.getMessage());
			request.setAttribute("Message", e.getMessage());
			return  "/NewUserPage.jsp";
		}
		Cookie ck = new Cookie("username", request.getParameter("user"));
		hibernateBudgetManagerDAO.Add_Category(user, "LEISURE");
		hibernateBudgetManagerDAO.Add_Category(user, "FOOD");
		hibernateBudgetManagerDAO.Add_Category(user, "CLOTHES");
		List<Category> categories = hibernateBudgetManagerDAO.Get_Uset_Categories(user);
		//set max age for cookie
		ck.setMaxAge(600);
		response.addCookie(ck);
		request.setAttribute("user", user);
		//add to the session the user and all his categories
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("categories", categories);
		return  "/UserPage.jsp";
		
	
	}
private String TransactionnPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SaveDataFailed {
	

            	List<Item> listItem  = (List<Item>) request.getSession().getAttribute("Items");
            	
            	String stringValue = request.getParameter("price");// item price
            	String item = request.getParameter("item"); // item name
            	String mode=  request.getParameter("radio"); // inc / dec
            	String  category = request.getParameter("categories"); //witch category the item will add to
            	

            	User user = (User) request.getSession().getAttribute("user");
            	try {
					hibernateBudgetManagerDAO.Add_Item(user,item, stringValue,mode,category,listItem);
				} catch (WrongInput e) {
					// TODO Auto-generated catch block
					log(e.getMessage());
					 geCategoriesFromSession(request);
					 return  "/TransactionPage.jsp";		
				} catch (BudgetIsOver e) {
					// TODO Auto-generated catch block
					log(e.getMessage());
					request.setAttribute("Message", e.toString());
				}
            	
            	//Creating a new  transactionn 
            	listItem = hibernateBudgetManagerDAO.Get_User_Items_by_Date(user);
				request.setAttribute("Items", listItem);
				request.setAttribute("user", user);			
				request.getSession().setAttribute("Items", listItem);
				
				return "/UserPage.jsp";
  
			
		

		
	}
/***
 * Update the user parameters
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 * @throws SaveDataFailed
 */
private String changeBudgetForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SaveDataFailed {
	Cookie cookie = null;
	Cookie[] cookies = null;
	cookies = request.getCookies();
	if (cookies == null) {
		//Could not find user returning to main screen to reconnect
		return  "/MainPage.jsp";
	}
	
	for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i];
        if (cookie.getName().equals("username")) {
        	String name = cookie.getValue();
        	// Obtains the user's information
        	User user = (User) request.getSession().getAttribute("user");
        	String budget = request.getParameter("budget");
        	String pass_old = request.getParameter("Password");// User current password
        	String pass = request.getParameter("Password1"); // The use new password
        	String pass2 = request.getParameter("Password2");// Re typing the new password
        	String frequency = request.getParameter("radio");
        	
        	//If the user was trying to update the password and type the correct old password 
			if (pass_old != null && user.getPassword().equals(pass_old)) {

					if (pass != null && pass2 != null) {
						if (pass.equals(pass2)) {
							try {
								hibernateBudgetManagerDAO.Update_Password(user, pass);
							} catch (WrongInput e) {
								// TODO Auto-generated catch block
								log(e.getMessage());
								return "/ChangeBudgetPage.jsp";
							}
						}
					}
				}
        	
        	
        	if(frequency!=null)// If the frequency was change by the user
        	{
        		hibernateBudgetManagerDAO.Update_frequency(user, frequency);
        	}
        	
        	// Taking the information of the budget 
        	if(budget!=null){
        		if(!budget.equals(""))// If the Budget was update by the user
        		{
						try {
							hibernateBudgetManagerDAO.Update_budget(user, budget);
						} catch (WrongInput e) {
							// TODO Auto-generated catch block
							log(e.getMessage());
							return "/ChangeBudgetPage.jsp";
						}
					}

				}
        		
        	//Update the user in the session 
        	geItemsFromSession(request);
        	 user = hibernateBudgetManagerDAO.GetUser(user.getName());
        	 request.getSession().setAttribute("user", user);
			request.setAttribute("user", user);			
			return "/UserPage.jsp";

		}
	}
	
	
	return  "/MainPage.jsp";
	}




}

