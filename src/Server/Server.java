package Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Server.SignIn;
import Data.Course;
import Data.User;

public class Server {
		static JSONArray array;
@SuppressWarnings("unchecked")
public static void main(String[] args) {

	
		array=new JSONArray();
		String message=" ";
		int port=4000;

		SignIn s=new SignIn();
		
		try {
			ServerSocket ss=new ServerSocket(port);
			MyServerSocket mSocket= new MyServerSocket(port, ss);
			while(!message.equals("exit")){
				
				mSocket.initialize();
				
				JSONObject inputObject=(JSONObject) mSocket.read();
				
				message=(String) inputObject.get("message");
				
				JSONObject outputObject=new JSONObject();
				
				switch (message) {
				case "login":{
					outputObject=s.login(inputObject);
					}
					break;
				case "register":{
					outputObject=s.register(inputObject);
				}
					break;
				case "update user":{
					System.out.println("update user request object "+inputObject.toString());
					User user =(User) inputObject.get("user");
					user.display();
//					array.add("a");
//					array.add("b");
//					array.add("c");
//					array.add("d");
					MySqlAccess a = new MySqlAccess() {

						@Override
						void writeResultSet(ResultSet resultSet2)
								throws Exception {
							
							
						}
					};
					int rno=Integer.parseInt(user.getRollno());
					String query="update student set userObject = ? where rollno = ?";
					PreparedStatement ps;
					ps=a.getPreparedStatement(query);
					ByteArrayOutputStream baos=new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					oos.writeObject(user);
					oos.close();
					Blob b1=a.connect.createBlob();
					b1.setBytes(1, baos.toByteArray());
					ps.setBlob(1,b1);
					ps.setInt(2, rno);
					a.executePreparedStatement(ps);
					a.deletequery("delete from CourseMapping where studentid = "+user.getRollno());
					for(Course c : user.getPriorityQueue()){
						
						ps=a.getPreparedStatement("Insert into CourseMapping values (?,?,?)");
						System.out.printf("rno %d courseid %d prio %d", rno,c.getId(),c.getPriority());
						ps.setInt(1, rno);
						ps.setInt(2, c.getId());
						ps.setInt(3, c.getPriority());
						a.executePreparedStatement(ps);
					}
					outputObject=new JSONObject();
					outputObject.put("message", "updated");
				}
					break;
				case "get courses":{
					array.clear();
					outputObject=new JSONObject();
					MySqlAccess a= new MySqlAccess() {

						@Override
						void writeResultSet(ResultSet resultSet2) throws Exception {
							 while (resultSet2.next()) {
								 JSONObject jo=new JSONObject();
								 String name = resultSet2.getString("name");
								 int id = resultSet2.getInt("id");
								 int dur = resultSet2.getInt("duration");
								 jo.put("name",name);
								 jo.put("id",id);
								 jo.put("dur",dur);
								 array.add(jo);
							 }
						}
					};
					try {
						a.readDataBase("select * from AuditCourse");
					} catch (Exception e) {
						e.printStackTrace();
					}
//					array.add("a");
//					array.add("b");
//					array.add("c");
//					array.add("d");
					outputObject.put("names", array);
				}
					break;
				case "exit":
					outputObject=new JSONObject();
					outputObject.put("message","bye" );
					break;
				default:
					break;
				}
				
//				User user =(User)mSocket.read();
//				user.display();
				
//				JSONArray array=new JSONArray();
//				JSONObject jsonObject=new JSONObject();
//				array.add("a");
//				array.add("b");
//				array.add("c");
//				array.add("d");
//				
//				jsonObject.put("names", array);
//				
//				String output = jsonObject.toString();
				mSocket.write(outputObject);
				mSocket.close();
			}
//			ss.close();
			mSocket.finalize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}

class MyServerSocket {
	private int port=4000;
	private ServerSocket ss;
	private ObjectInputStream inputStream ;
	private ObjectOutputStream outputStream ;
	private Socket socket;
	public MyServerSocket(int port, ServerSocket ss) throws IOException {
		super();
		this.port = 8080;
		this.ss =new ServerSocket(this.port);
	}
	
	public void initialize(){
		try {
			socket = ss.accept();
			inputStream=new ObjectInputStream(socket.getInputStream());
			outputStream =new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object read(){
		Object object=null;
		try {
			 object=inputStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}
	public void write(Object object){
		try {
			outputStream.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		ss.close();
	}
	
	
}


