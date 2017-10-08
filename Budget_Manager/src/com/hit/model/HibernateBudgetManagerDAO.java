package com.hit.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.hit.objects.Category;
import com.hit.objects.Item;
import com.hit.objects.User;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

public class HibernateBudgetManagerDAO implements IBudgetManagerDAO{
	 SessionFactory factory ;
	 Date date;

	 private  static  HibernateBudgetManagerDAO hibernateToDoListDAO=null;
	 	
	 //Get an instance of HibernateBudgetManagerDAO
	    public  static HibernateBudgetManagerDAO getInstance(){
	        if (hibernateToDoListDAO == null){
	        	hibernateToDoListDAO = new HibernateBudgetManagerDAO();
	        }
	        return  hibernateToDoListDAO;
	    }
	    
        private HibernateBudgetManagerDAO() {
			// TODO Auto-generated constructor stub
        	factory = new AnnotationConfiguration().configure().buildSessionFactory();
        	date = new Date();
		}
        
        
        public  void GetDataUsers() throws SaveDataFailed{
            //creating a new session for getting all users
        	Session anotherSession = null;
        	
        	try {
        		anotherSession = factory.openSession();
        		anotherSession.beginTransaction();
        		List users = anotherSession.createQuery("from User").list();
        		System.out.println("There are " + users.size() + " product(s)");
        		Iterator i = users.iterator();
        		while(i.hasNext())//Printing the users in the database
        		{
        			System.out.println(i.next());
        		}
        		} catch (HibernateException e) {
				if (anotherSession.getTransaction() != null) {
					anotherSession.getTransaction().rollback();
				}
				
				throw new SaveDataFailed("Failed to save information");
			}
        	
        	finally {
                anotherSession.close();

			}
          
        }

        
        public User GetUser(String name) throws SaveDataFailed{
        	boolean flag=false;
        	Session session = null;
        	User a=null;
        	try {
        		session= factory.openSession();
        		session.beginTransaction();
        		List users = session.createQuery("from User").list();
        		for(int i=0;i<users.size();i++)
        			{
        			a = (User)users.get(i);
        			if(a.getName().equals(name))//If the user name is the requested user
        			{
        				flag=true;
        				break;
        			}
        			}
        		} catch (HibernateException e) {
        			if (session.getTransaction() != null) {
        				session.getTransaction().rollback();
        			}
        			throw new SaveDataFailed("Failed to save information");
        		}
        	
        		finally {
        			session.close();

        		}
         if (flag) {
        	 return a;
		}else
            return null;
        }
        
        public boolean User_exist(String name) throws SaveDataFailed {
        
        	Session anotherSession=null;
        	Query query= null;
        	List users;
        try {
        	 anotherSession = factory.openSession();
        	anotherSession.beginTransaction();
        	String hq1= "from User  t WHERE  t.name = :name";//Recive the user with the same name frome the database
        	 query = anotherSession.createQuery(hq1);
        	 query.setParameter("name", name);
              users= query.list();
              
        } catch (HibernateException e) {
			if (anotherSession.getTransaction() != null) {
				anotherSession.getTransaction().rollback();
			}
			throw new SaveDataFailed("Failed to save information");
		}
    	
    	finally {
    		anotherSession.close();

		}
       
       
        
        for(int i=0;i<users.size();i++)
        {
        	User a = (User)users.get(i);
            if(a.getName().equals(name))//If the name already exist in database
            	return true;
        }
        return false;
       
    }

		
		public void Delete_User(User u) throws SaveDataFailed {
			// TODO Auto-generated method stub
				Session anotherSession=null;
				 try {
					 anotherSession = factory.openSession();
					 anotherSession.beginTransaction();
					 anotherSession.delete(u);//Deleting the requested user from the database
					 anotherSession.getTransaction().commit();
				 } catch (HibernateException e) {
						if (anotherSession.getTransaction() != null) {
							anotherSession.getTransaction().rollback();
						}
						throw new SaveDataFailed("Failed to save information");
					}
			    	
			    	finally {
			    		anotherSession.close();

					}
	          
		}

        
        

		@Override
		public User AddUser(String name, String password, String sBudget, String frequency) throws WrongInput, UserExistException, SaveDataFailed {
		
		
			
			 User p1=null;
			 Session session=null;
			
			// TODO Auto-generated method stub
			if(User_exist(name))
				throw new UserExistException();
			 try {
			session = factory.openSession();
	        session.beginTransaction();
	        p1 = new User(name,password,sBudget,frequency);
	        session.save(p1);//Add the new user into the database 
	        session.getTransaction().commit();
			 } catch (HibernateException e) {
					if (session.getTransaction() != null) {
						session.getTransaction().rollback();
					}
					throw new SaveDataFailed("Failed to save information");
				}
		    	
		    	finally {
		    		session.close();

				}
	        return p1;
		}

		@Override
		public void Update_budget(User u, String sBudget) throws WrongInput, SaveDataFailed {

			// TODO Auto-generated method stub
			int budget= Integer.parseInt(sBudget);
			if(u.getBudget()==budget)
				return;
			 Session anotherSession = null ;
			 try {
				 anotherSession = factory.openSession();
		        anotherSession.beginTransaction();
		        u.setBudget(sBudget);//Update the user reference that the method recive
		        anotherSession.update(u);//Update the user in the database
		        anotherSession.getTransaction().commit();
			 } catch (HibernateException e) {
					if (anotherSession.getTransaction() != null) {
						anotherSession.getTransaction().rollback();
					}
					throw new SaveDataFailed("Failed to save information");
				}
		    	
		    	finally {
		    		anotherSession.close();

				} 
		}

	

		@Override
		public void Add_Item(User user, String item, String sValue,String mode, String category,List<Item> items) throws WrongInput, BudgetIsOver, SaveDataFailed{
			// TODO Auto-generated method stub
			
			
	        double sum =0;
	        Item a = new Item(user.getId(),item,sValue,mode,category);
	        sum +=a.getPrice();
	        if (items !=null) {
	        	for (int i = 0; i < items.size(); i++) {
	        		sum += items.get(i).getPrice();
	        	}   	
	        }
	      
	        Session session = null;
	        try {
			 session = factory.openSession();
	        session.beginTransaction();
	       
	        session.save(a);//Add a new item into the database
	        session.getTransaction().commit();
	        } catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
	    	
	    	finally {
	    		session.close();

			} 
	        if (sum >= user.getBudget()) {
				throw new BudgetIsOver(sum -user.getBudget() );
			}

		}
		
		@Override
		public void Delete_Item(Item i) throws SaveDataFailed {
			// TODO Auto-generated method stub
			Session anotherSession = null;
			 try {
			anotherSession = factory.openSession();
            anotherSession.beginTransaction();
            anotherSession.delete(i);//Delete the requested item from the database
            anotherSession.getTransaction().commit();
			 } catch (HibernateException e) {
					if (anotherSession.getTransaction() != null) {
						anotherSession.getTransaction().rollback();
					}
					throw new SaveDataFailed("Failed to save information");
				}
		    	
		    	finally {
		    		anotherSession.close();

				} 
		}
		
		@Override
		public Item Get_Item(String idString) throws SaveDataFailed, WrongInput {
			// TODO Auto-generated method stub
			
			if (idString.contains("[a-zA-Z]+")|| idString.equals("")) {
				throw new WrongInput();
			}
			
			int id = Integer.parseInt(idString);
			Session anotherSession = null;
			 Item a=null;
			try {
			 anotherSession = factory.openSession();
	        anotherSession.beginTransaction();
	        String hq1= "from Item t WHERE  t.id = :id";
	        Query query = anotherSession.createQuery(hq1);
	        query.setParameter("id", id);//Define the parameter id in the request from the database to the item id that the method recived 
	         a = (Item) query.uniqueResult();//Recive the item from the database
			} catch (HibernateException e) {
				if (anotherSession.getTransaction() != null) {
					anotherSession.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
	    	
	    	finally {
	    		anotherSession.close();

			} 
            if (a.getId() == id)
            		return a;

            return null;
		}
		

	
		@Override
		public void Update_Password(User u, String password) throws SaveDataFailed, WrongInput {
			// TODO Auto-generated method stub
			if(u.getPassword().equals(password))
				return;
				Session anotherSession = null;
				try {
			    anotherSession = factory.openSession();
		        anotherSession.beginTransaction();
		        u.setPassword(password);//Update the user reference that the method recive
		        anotherSession.update(u);//Update the user in the database
		        anotherSession.getTransaction().commit();
				} catch (HibernateException e) {
					if (anotherSession.getTransaction() != null) {
						anotherSession.getTransaction().rollback();
					}
					throw new SaveDataFailed("Failed to save information");
				}
		    	
		    	finally {
		    		anotherSession.close();

				} 
		        
		}

		@Override
		public void Update_frequency(User u, String frequency) throws SaveDataFailed {
			// TODO Auto-generated method stub
			Session anotherSession = null;
			try {
				anotherSession = factory.openSession();
				
		        anotherSession.beginTransaction();
		        u.setFrequency(frequency);//Update the user reference that the method recive
		        anotherSession.update(u);//Update the user in the database
		        anotherSession.getTransaction().commit();
			} catch (HibernateException e) {
				if (anotherSession.getTransaction() != null) {
					anotherSession.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
	    	
	    	finally {
	    		anotherSession.close();

			} 
		        
		}

		@Override
		public void Update_Item_Name(Item i, String name) throws SaveDataFailed, WrongInput {
			// TODO Auto-generated method stub
			if(i.getItem().equals(name))
				return;
			Session anotherSession = null;
			try {
				anotherSession = factory.openSession();
		        anotherSession.beginTransaction();
		        i.setItem(name);//Update the item reference that the method recive
		        anotherSession.update(i);//Update the item in the database
		        anotherSession.getTransaction().commit();
			} catch (HibernateException e) {
				if (anotherSession.getTransaction() != null) {
					anotherSession.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
	    	
	    	finally {
	    		anotherSession.close();

			}
		}

		@Override
		public void Update_Item_Price(Item i,  String itemPrice) throws SaveDataFailed, WrongInput {
			// TODO Auto-generated method stub
			double price=0;
			try{
			 price = Double.parseDouble(itemPrice);
			}catch (NumberFormatException e) {
				throw new WrongInput();
			}
			if(i.getPrice()==price)
				return;
			Session anotherSession = null;
			try {
				anotherSession = factory.openSession();
	        anotherSession.beginTransaction();
	        i.setPrice(itemPrice);//Update the item reference that the method recive
	        anotherSession.update(i);//Update the item in the database
	        anotherSession.getTransaction().commit();
			} catch (HibernateException e) {
				if (anotherSession.getTransaction() != null) {
					anotherSession.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
	    	
	    	finally {
	    		anotherSession.close();

			}
		}

		@Override
		public List<Item> Get_User_Items_by_Date(User u) throws SaveDataFailed {
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd ");
			Calendar cal = Calendar.getInstance();
			// TODO Auto-generated method stub
			Date start,end;
			Calendar first;
			Calendar last;
			switch (u.getFrequency()) {
			case 1:
			
					start =cal.getTime();
					cal.add(Calendar.DAY_OF_WEEK, 1);
					end = cal.getTime();
				
	
				break;
			default:
			case 7:
				 first = (Calendar) cal.clone();
				 first.add(Calendar.DAY_OF_WEEK,  first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));
				  last = (Calendar) first.clone();
				    last.add(Calendar.DAY_OF_YEAR, 7);
			
					start =first.getTime();
					end = last.getTime();

				break;
				
			case 30:
				 
				 first = (Calendar) cal.clone();
				 first.add(Calendar.DAY_OF_MONTH,  cal.getMinimalDaysInFirstWeek() - first.get(Calendar.DAY_OF_MONTH));
				  last = (Calendar) first.clone();
				    last.add(Calendar.DAY_OF_YEAR, 30);
			
					start =first.getTime();
					end = last.getTime();
			

			break;


			}
			 List<Item> items = new ArrayList<>();
			Session anotherSession = null;
			try {
				anotherSession = factory.openSession();
	        anotherSession.beginTransaction();
	        String hq1= "from Item t WHERE  t.userId = :id And Date between ' "+ dtf.format(start) + "' and '" +dtf.format(end)  + "' ";
	        Query query = anotherSession.createQuery(hq1);
	        query.setParameter("id", u.getId());
	        items = (List<Item>)query.list();//Recive a list of the item of the requested user
			} catch (HibernateException e) {
				if (anotherSession.getTransaction() != null) {
					anotherSession.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
	    	
	    	finally {
	    		anotherSession.close();

			}
			return items;
		}

		@Override
		public void Add_Category(User u, String name) throws SaveDataFailed {
			// TODO Auto-generated method stub
			if(!Category_Exist(u, name))
			{
				Session session = null;
				try {
					session = factory.openSession();
					session.beginTransaction();
					Category c = new Category(u.getId(),name);
					session.save(c);//Add the new category into the database
					session.getTransaction().commit();
				} catch (HibernateException e) {
					if (session.getTransaction() != null) {
						session.getTransaction().rollback();
					}
					throw new SaveDataFailed("Failed to save information");
				}
		    	
		    	finally {
		    		session.close();

				}
			}
		}

		@Override
		public void Delete_Category(Category c) throws SaveDataFailed {
			// TODO Auto-generated method stub
			Session session = null;
			try {
				session = factory.openSession();
				session.beginTransaction();
				session.delete(c);//Delete the requested category from the database
				session.getTransaction().commit();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
	    	
	    	finally {
	    		session.close();

			}
		}

		@Override
		public void Update_Category_name(Category c,String name,User user) throws SaveDataFailed, WrongInput {
			// TODO Auto-generated method stub
			String oldName;
			if(c.getCategoryName().equals(name))
				return;
			oldName = c.getCategoryName();
			Session session = null;
			try {
				session = factory.openSession();
				session.beginTransaction();
				c.setCategoryName(name);//Update the category reference that the method recive
				session.update(c);//Update the category in the database
				session.getTransaction().commit();
				} catch (HibernateException e) {
					if (session.getTransaction() != null) {
						session.getTransaction().rollback();
					}
					throw new SaveDataFailed("Failed to save information");
				}
	    	
				finally {
					session.close();

				}
			List< Item> items = Get_User_Items(user);
			for (Item item : items) {
				if (item.getCategory().equals(oldName)) {
					Update_Item_Category(item,name);
				}
			}
			
			
			
			
		}

		@Override
		public List<Category> Get_Uset_Categories(User u) throws SaveDataFailed {
			// TODO Auto-generated method stub
			Session anotherSession = null;
			 List<Category> categories = null;
			try {
				anotherSession = factory.openSession();
	        anotherSession.beginTransaction();
	        String hq1= "from Category  WHERE  user_id = :id";
	        Query query = anotherSession.createQuery(hq1);
	        query.setParameter("id", u.getId());
	        categories = (List<Category>)query.list();//Recive a list of the categories that the user has
			} catch (HibernateException e) {
				if (anotherSession.getTransaction() != null) {
					anotherSession.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
    	
			finally {
				anotherSession.close();

			}
			return categories;
		}

		

		@Override
		public boolean Category_Exist(User u, String name) throws SaveDataFailed {
			// TODO Auto-generated method stub
			List<Category> list = Get_Uset_Categories(u);
			
	        for(int i=0;i<list.size();i++)
	        {
	        	Category c = (Category)list.get(i);
	            if(c.getCategoryName().equals(name))//Check if the category name already exist
	            	return true;
	        }
	        return false;
		}

		@Override
		public Category Get_User_Category(User u, String name) throws SaveDataFailed {
			// TODO Auto-generated method stub
			List<Category> list = Get_Uset_Categories(u);//Check if the category name already exist
			
	        for(int i=0;i<list.size();i++)
	        {
	        	Category c = (Category)list.get(i);
	            if(c.getCategoryName().equals(name))//Check if the category name from the list is the request category name
	            	return c;
	        }
	        return null;// In case that there is no category name with the name that requested
		}

		@Override
		public List<User> GetUsers() throws SaveDataFailed {
			Session session = null;
			List<User> users = null;
			try {
				session = factory.openSession();
            session.beginTransaction();
          users = session.createQuery("from User").list();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
    	
			finally {
				session.close();

			}
           
            return users;
		}


		@Override
		public Category Get_User_Category(User u, int id) throws SaveDataFailed {
			List<Category> list = Get_Uset_Categories(u);//Check if the category name already exist
			
	        for(int i=0;i<list.size();i++)
	        {
	        	Category c = (Category)list.get(i);
	            if(c.getId()==id)//Check if the category name from the list is the request category name
	            	return c;
	        }
	        return null;// In case that there is no category name with the name that requested
		}

		@Override
		public void Update_Item_Category(Item i, String Category) throws SaveDataFailed, WrongInput {
			// TODO Auto-generated method stub
			if(i.getCategory().equals(Category))
				return;
			
			Session anotherSession = null;
			try {
				anotherSession = factory.openSession();
		        anotherSession.beginTransaction();
		        i.setCategory(Category);//Update the item reference that the method recive
		        anotherSession.update(i);//Update the item in the database
		        anotherSession.getTransaction().commit();
			} catch (HibernateException e) {
				if (anotherSession.getTransaction() != null) {
					anotherSession.getTransaction().rollback();
				}
				throw new SaveDataFailed("Failed to save information");
			}
	    	
	    	finally {
	    		anotherSession.close();

			}
		}

		@Override
		public List<Item> Get_User_Items(User u) throws SaveDataFailed {
			// TODO Auto-generated method stub
			 List<Item> items = new ArrayList<>();
				Session anotherSession = null;
				try {
					anotherSession = factory.openSession();
		        anotherSession.beginTransaction();
		        String hq1= "from Item t WHERE  t.userId = :id ";
		        Query query = anotherSession.createQuery(hq1);
		        query.setParameter("id", u.getId());
		        items = (List<Item>)query.list();//Recive a list of the item of the requested user
				} catch (HibernateException e) {
					if (anotherSession.getTransaction() != null) {
						anotherSession.getTransaction().rollback();
					}
					throw new SaveDataFailed("Failed to save information");
				}
		    	
		    	finally {
		    		anotherSession.close();

				}
				return items;
		}
		

	

}
