package Client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import Data.User;

public class SignIn {

	//TODO: add server code 
	HashMap<String,String> pwMapping = new HashMap<String, String>();
	HashMap<String, User> userMap=new HashMap<String, User>();
	HashSet<User> users= new HashSet<User>();
	
	Scanner sc=new Scanner(System.in);
	
	public User login(){
		User user=null;
		String username,pw;
		username=sc.next();
		if(users.contains(userMap.get(username))){
			System.out.println("Enter password");
			pw=sc.next();	
			if(pwMapping.get(username).equals(pw)){
				user=userMap.get(username);
			}
			else
				System.out.println("Incorrect password try again");
				
		}else{
			System.out.println("User does not exist,please register");
		}
		return user;
		
	}
	public User register(){
		User user=null;
		String username,pw;
		System.out.println("Enter username");
		username=sc.next();
		if(users.contains(userMap.get(username))){
			System.out.println("User already exists!");
		}else{
			String name,rollno;

			System.out.println("Enter Name");
			name=sc.next();
			System.out.println("Enter Rollno");
			rollno=sc.next();
			user =new User(name, rollno);
			
			System.out.println("Enter password");
			pw=sc.next();
			
			users.add(user);
			pwMapping.put(username, pw);
			userMap.put(username, user);
			
		}
		return user;
	}

}
