package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Data.User;

public class Server {

public static void main(String[] args) {
		
		int port=4000;
		try {
			ServerSocket ss=new ServerSocket(port);
			MyServerSocket mSocket= new MyServerSocket(port, ss);
			for(int i=0;i<5;i++){
//				Socket socket=ss.accept();
//				ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
//				ObjectOutputStream outputStream =new ObjectOutputStream(socket.getOutputStream());
//				
//				User user=(User)inputStream.readObject();
//				String input=user.toString();
//				user.display();
//				System.out.println(input);
//				
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
//
//				outputStream.writeObject(jsonObject);
//				outputStream.close();
//				inputStream.close();
//				socket.close();
				mSocket.initialize();
				User user =(User)mSocket.read();
				user.display();
				
				JSONArray array=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				array.add("a");
				array.add("b");
				array.add("c");
				array.add("d");
				
				jsonObject.put("names", array);
				
				String output = jsonObject.toString();
				mSocket.write(jsonObject);
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


