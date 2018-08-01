package Client;

import Data.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MainActivity {
	
	static MySocket m=new MySocket();
	static ArrayList<Course> listOfCourses=new ArrayList<Course>();
	static User currentUser=null;
	
	public static void main(String[] args) throws Throwable {
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
				currentUser=s.login();
				break;
			case 2:
				currentUser=s.register();
				break;
			case 3:
				m.initialize();
				listOfCourses.clear();
				m.write(currentUser);
				JSONObject o=m.listen();
				JSONArray array=(JSONArray) o.get("names");
		        for(int i=0;i<array.size();i++){
		        	listOfCourses.add(new Course(array.get(i).toString(), "4hrs"));
		        }
		        for (int i = 0; i < listOfCourses.size(); i++) {
		        	System.out.println(listOfCourses.get(i).getName());
				}
				break;
			case 4:
				currentUser.setPriorityQueue(listOfCourses);
				break;
			case 5:
				
				break;

			default:
				break;
			}
		}while(choice!=5);
		m.finalize();
	}
}


class MySocket{
	Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
	InetAddress host;
	
	public void initialize(){
		try {
			host = InetAddress.getLocalHost();
	        socket = new Socket(host.getHostName(), 8080);
	        oos = null;
	        ois = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(Object object){
        
        try {
        	oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(object);
//			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public JSONObject listen(){
		JSONObject o=null;
		try{	        
	        ois=new ObjectInputStream(socket.getInputStream());
	        o=(JSONObject)ois.readObject();
	        System.out.println(o.toString());
//	        ois.close();
	    } catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
        socket.close();
	}
	
}


/*
 * 

himan
himanshu
3346
3346

*/