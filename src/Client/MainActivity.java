package Client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Data.Course;
import Data.User;
import Data.Utility;

public class MainActivity {
	
	static ArrayList<Course> listOfCourses=new ArrayList<Course>();
	static User currentUser=null;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Throwable {
		MySocket m=new MySocket();
		Utility.initialize();
//		m.initialize();
		
		SignIn s=new SignIn();
		
		int choice=0;
		
		
		do{
			System.out.println("Enter \n1. to login");
			System.out.println("Enter \n2. to register");
			System.out.println("Enter \n3. to See courses");
			System.out.println("Enter \n4. to Assign preferences");
			System.out.println("Enter \n5. to Exit");
			
			choice= Utility.sc.nextInt();
			switch (choice) {
			case 1:
//				currentUser=s.login(m);
				break;
			case 2:
//				currentUser=s.register(m);
				break;
			case 3:{
				listOfCourses.clear();
				
				JSONObject input=new JSONObject();
//				input.put("user", currentUser);
				input.put("message", "get courses");
				
				m.write(input);
				JSONObject o=m.listen();
				
				JSONArray array=(JSONArray) o.get("names");
		        for(int i=0;i<array.size();i++){
		        	JSONObject jo=(JSONObject) array.get(i);
		        	String name;int id;int dur;
		        	name=(String) jo.get("name");
		        	id=(int) jo.get("id");
		        	dur=(int) jo.get("dur");
		        	listOfCourses.add(new Course(name,dur,id));
		        }
		        for (int i = 0; i < listOfCourses.size(); i++) {
		        	System.out.println(listOfCourses.get(i).getName());
				}
			}
				break;
			case 4:{
				if(listOfCourses.size()==0) {
					System.out.println("load courses first");
					break;
				}
				if(currentUser==null){
					System.out.println("please register or login");
					break;
				}
				currentUser.setPriorityQueue(listOfCourses);
				JSONObject input2=new JSONObject();
				input2.put("user", currentUser);
				input2.put("message", "update user");
				m.write(input2);
				JSONObject o2=m.listen();
			}
				break;
			case 5:
				JSONObject ip3=new JSONObject();
				ip3.put("message", "exit");
				m.write(ip3);
				JSONObject o2=m.listen();
				break;

			default:
				break;
			}
		}while(choice!=5);
		m.finalize();
	}
}



/*
 * 
 grant all on te3346db.* to ‘te3346’@’localhost’ identified by ‘te3346’
himan
himanshu
3346
3346

*/
