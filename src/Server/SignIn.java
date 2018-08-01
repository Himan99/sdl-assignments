package Server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.json.simple.JSONObject;

import Data.User;

public class SignIn {

	//TODO: add server code 
	HashMap<String,String> pwMapping = new HashMap<String, String>();
	HashMap<String, User> userMap=new HashMap<String, User>();
	HashSet<User> users= new HashSet<User>();
	
	Scanner sc=new Scanner(System.in);
	
	public JSONObject login(JSONObject object){
		User user=null;
		String username,pw,error;
		
		username=(String) object.get("username");
		
		if(users.contains(userMap.get(username))){
			
//			System.out.println("Enter password");
			pw=(String) object.get("pw");	
			
			if(pwMapping.get(username).equals(pw)){
				user=userMap.get(username);
			}
			
			else
				System.out.println("Incorrect password try again");
				error="Incorrect password try again";
		}else{
			System.out.println("User does not exist,please register");
			error="User does not exist,please register";
		}

		JSONObject loginResponse=new JSONObject();
		if(user != null){
			loginResponse.put("user", user);
			System.out.println(user.getName()+" logged in");
		}else{
			loginResponse.put("error",error);
		}
		return loginResponse;
		
	}
	public JSONObject register(JSONObject object){
		User user=null;
		String username,pw,error=" ";
		username=(String) object.get("username");
		if(users.contains(userMap.get(username))){
			System.out.println("User already exists!");
			error="User already exists!";
		}else{
			String name,rollno;

			name=(String) object.get("name");
			rollno=(String) object.get("rollno");
			user =new User(name, rollno);
			pw=(String) object.get("pw");
			users.add(user);
			pwMapping.put(username, pw);
			userMap.put(username, user);
		}

		JSONObject registerResponse=new JSONObject();
		if(user != null){
			registerResponse.put("user", user);
			System.out.println(user.getName()+" signed in");
		}else{
			registerResponse.put("error",error);
		}
		
		return registerResponse;
	}

}
