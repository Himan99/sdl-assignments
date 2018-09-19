package Client;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Data.Course;
import Data.User;
import Data.Utility;

public class SeverConnectClass {
	static ArrayList<Course> listOfCourses=new ArrayList<Course>();
	public static User currentUser=null;
	static MySocket m=new MySocket();	
	static SignIn s=new SignIn();

	public static void login(String username,String pw) throws Throwable{
		currentUser=s.login(m,username,pw);
	}
	public static void register(JSONObject object) throws Throwable{
		currentUser=s.register(m,object);
	}
	public static  ArrayList<Course> getCourses(){
		listOfCourses.clear();
		
		JSONObject input=new JSONObject();
//		input.put("user", currentUser);
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
        return listOfCourses;
	}
	public static String submitResponse(ArrayList<Course> list){
		currentUser.setPriorityQueue(list);
		JSONObject input2=new JSONObject();
		input2.put("user", currentUser);
		input2.put("message", "update user");
		m.write(input2);
		JSONObject o2=m.listen();
		return (String) o2.get("message");
	}
	public static String exit(){
		JSONObject ip3=new JSONObject();
		ip3.put("message", "exit");
		m.write(ip3);
		JSONObject o2=m.listen();
		try {
			m.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return (String) o2.get("message");
	}
	
}
