package Server;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.sql.rowset.serial.SerialBlob;

import org.json.simple.JSONObject;

import Data.User;

public class SignIn {

	//TODO: add server code 
	HashMap<String,String> pwMapping = new HashMap<String, String>();
	HashMap<String, User> userMap=new HashMap<String, User>();
	HashSet<User> users= new HashSet<User>();
	
	Scanner sc=new Scanner(System.in);
	
	
	
	public SignIn() {
		MySqlAccess a = new MySqlAccess() {

			@Override
			void writeResultSet(ResultSet resultSet2) throws Exception {
				while (resultSet2.next()) {
					 String username = resultSet2.getString("username");
					 String pw = resultSet2.getString("username");
					 ObjectInputStream ios=new ObjectInputStream();
					 User temp = 
				 }
			}
		};
		String query="select username,pw from student";
		a.readDataBase(query);
	}
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
			MySqlAccess a= new MySqlAccess() {

				@Override
				void writeResultSet(ResultSet resultSet2) {
					
				}
			};
			try {
				String query="insert into  student values (?,?,?,?,?,?,?,?)";
				PreparedStatement ps;
				ps=a.getPreparedStatement(query);
				ps.setString(1, name);
				ps.setInt(2, Integer.valueOf(rollno));
				ps.setString(3,user.getDept());
				ps.setInt(4, user.getDiv());
				ps.setInt(5, user.getYear());
				ps.setString(6, username);
				ps.setString(7, pw);
				ByteArrayOutputStream baos=new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(user);
				oos.close();
				Blob b1=a.connect.createBlob();
				b1.setBytes(1, baos.toByteArray());
				ps.setBlob(8,b1);
				a.executePreparedStatement(ps);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
