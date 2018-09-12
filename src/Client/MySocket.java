package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.json.simple.JSONObject;

class MySocket{
	Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
	InetAddress host;
	
public MySocket() {
		try {
			host = InetAddress.getLocalHost();
	        socket = new Socket(host.getHostName(), 4000);
//	        oos = null;
        	oos = new ObjectOutputStream(socket.getOutputStream());
//	        ois = null;
	        ois=new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	public void initialize(){
//		try {
//			host = InetAddress.getLocalHost();
//	        socket = new Socket(host.getHostName(), 8080);
//	        oos = null;
//	        ois = null;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	public void write(Object object){
        
        try {
			oos.writeObject(object);
//			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public JSONObject listen(){
		JSONObject o=null;
		try{	        
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
		oos.close();
		ois.close();
        socket.close();
	}
	
}