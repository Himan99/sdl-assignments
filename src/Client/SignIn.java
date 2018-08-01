package Client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.json.simple.JSONObject;

import Data.User;

public class SignIn {

	HashMap<String,String> pwMapping = new HashMap<String, String>();
	HashMap<String, User> userMap=new HashMap<String, User>();
	HashSet<User> users= new HashSet<User>();
	
	Scanner sc=new Scanner(System.in);
	
	public User login() throws Throwable{
		User mUser=null;
		JSONObject object=new JSONObject();
		String username,pw;
		
		System.out.println("Enter Username");
		username=sc.next();
		System.out.println("Enter password");
		pw=sc.next();
		
		object.put("username", username);
		object.put("pw", pw);
		object.put("message", "login");
		
		JSONObject outputObject;
		
		MySocket socket= new MySocket();
		socket.initialize();
		socket.write(object);
		outputObject=socket.listen();
		
		System.out.println("Login response object "+outputObject.toString());
		
		if(outputObject.containsKey("error")){
			System.out.println("Unsuccessful");
		}
		else{
			mUser=(User) outputObject.get("user");
		}
		socket.finalize();
		return mUser;
	}
	public User register() throws Throwable{
		User mUser=null;
		JSONObject object=new JSONObject();
		String username,pw;
		String name,rollno;
		
		System.out.println("Enter username");
		username=sc.next();
		
		System.out.println("Enter Name");
		name=sc.next();
		
		System.out.println("Enter Rollno");
		rollno=sc.next();
		
		System.out.println("Enter password");
		pw=sc.next();
		
		object.put("username", username);
		object.put("pw", pw);
		object.put("name", name);
		object.put("rollno", rollno);
		object.put("message", "register");
		
		
		JSONObject outputObject;
		
		MySocket socket= new MySocket();
		socket.initialize();
		socket.write(object);
		outputObject=socket.listen();
		
		System.out.println("register response object "+outputObject.toString());
		
		if(outputObject.containsKey("error")){
			System.out.println((String)outputObject.get("error"));
		}
		else{
			mUser=(User) outputObject.get("user");
		}

		socket.finalize();
		return mUser;
		
//		users.add(user);
//		pwMapping.put(username, pw);
//		userMap.put(username, user);
		
	}

}
