package ClientGUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;  

import org.json.simple.JSONObject;

import Client.SeverConnectClass;
import Data.Course;

public class MainScreen {
	static JFrame f;
	static SeverConnectClass scc;
	
	public static void main(String[] args) {
		f=new JFrame();
		scc=new SeverConnectClass();
		JPanel p=new JPanel();
		p.setBounds(10,10,380,340);    
        p.setBackground(Color.gray);  
        p.setLayout(null);
		JButton mLoginButton=new JButton("Login");
		JButton mRegisterButton=new JButton("Register");
		mLoginButton.setBounds(150, 100, 100, 50);
		mRegisterButton.setBounds(150, 200, 100, 50);
		mLoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
				f.setVisible(false);
				f.getContentPane().removeAll();
				f.getContentPane().add(getLoginPanel());
				f.setSize(400, 400);
				f.setLayout(null);
				f.setVisible(true);
			}
		});
		mRegisterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.getContentPane().removeAll();
				f.getContentPane().add(getRegisterPanel());
				f.setSize(400, 400);
				f.setLayout(null);
				f.setVisible(true);
			}
		});
		p.add(mRegisterButton);
		p.add(mLoginButton);
		f.getContentPane().add(p);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static JPanel getLoginPanel() {
		JPanel f1=new JPanel();
		JButton mLoginButton=new JButton("Login");
		mLoginButton.setBounds(150, 250, 100, 50);
		final JTextField mUsernameTF=new JTextField("Username");
		final JPasswordField mPasswordTF=new JPasswordField();
		mUsernameTF.setBounds(100, 100, 200, 50);
		mPasswordTF.setBounds(100, 150, 200, 50);
		mLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					String username,pw;
					username=mUsernameTF.getText();
					pw=String.valueOf(mPasswordTF.getPassword());
					scc.login(username,pw);
					if(scc.currentUser!=null){
						f.setVisible(false);
						f.getContentPane().removeAll();
						f.getContentPane().add(getSelectorPanel());
						f.setSize(556, 430);
						f.setLayout(null);
						f.setVisible(true);
					}
					
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		f1.add(mUsernameTF);
		f1.add(mPasswordTF);
		f1.add(mLoginButton);
		f1.setSize(400, 400);
		f1.setLayout(null);
		return f1;
	}
	public static JPanel getRegisterPanel() {
		JPanel f1=new JPanel();
		JButton mLoginButton=new JButton("Register");
		final JTextField mUsernameTF=new JTextField("Username");
		final JTextField mNameTF=new JTextField("Name");
		final JTextField mRollNoTF=new JTextField("Roll Number");
		final JPasswordField mPasswordTF=new JPasswordField();
		mUsernameTF.setBounds(100, 0, 200, 50);
		mNameTF.setBounds(100, 50, 200, 50);
		mRollNoTF.setBounds(100, 100, 200, 50);
		mPasswordTF.setBounds(100, 150, 200, 50);
		mLoginButton.setBounds(150, 250, 100, 50);
		mLoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username=mUsernameTF.getText();
				String pw=String.valueOf(mPasswordTF.getPassword());
				String rno=mRollNoTF.getText();
				String name=mNameTF.getText();

				JSONObject object = new JSONObject();
				object.put("username", username);
				object.put("pw", pw);
				object.put("name", name);
				object.put("rollno", rno);
				object.put("message", "register");
				try {
					scc.register(object);
					if(scc.currentUser!=null){
						f.setVisible(false);
						f.getContentPane().removeAll();
						f.getContentPane().add(getSelectorPanel());
						f.setSize(556, 430);
						f.setLayout(null);
						f.setVisible(true);
					}
					
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		f1.add(mUsernameTF);
		f1.add(mNameTF);
		f1.add(mRollNoTF);
		f1.add(mPasswordTF);
		f1.add(mLoginButton);
		f1.setSize(400, 400);
		f1.setLayout(null);
		return f1;
	}
	public static JPanel getSelectorPanel() {
		final ArrayList<Course> list1=scc.getCourses();
		final ArrayList<Course> list2=new ArrayList<Course>();
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 556, 367);
		panel.setLayout(null);
		

		final JList list = new JList(toStringArray(list1));
		list.setBounds(12, 100, 186, 184);
		panel.add(list);
		
		JButton buttonAdd = new JButton(">>>");
		buttonAdd.setBounds(243, 148, 64, 34);
		panel.add(buttonAdd);

		final JList list_1 = new JList();
		list_1.setValueIsAdjusting(true);
		list_1.setBounds(351, 100, 193, 184);
		panel.add(list_1);
		
		JButton btnDelete = new JButton("<<<");
		btnDelete.setBounds(243, 194, 64, 34);
		panel.add(btnDelete);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(32, 315, 117, 25);
		panel.add(btnSubmit);
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(219, 315, 117, 25);
		panel.add(btnReset);
		
		JButton btnReload = new JButton("Reload");
		btnReload.setBounds(396, 315, 117, 25);
		panel.add(btnReload);
		
		final JLabel lblMessage = new JLabel("message");
		lblMessage.setBackground(Color.LIGHT_GRAY);
		lblMessage.setBounds(12, 352, 447, 15);
		panel.add(lblMessage);
		
		JLabel lblAllCourses = new JLabel("All Courses");
		lblAllCourses.setBounds(12, 79, 117, 15);
		panel.add(lblAllCourses);
		
		JLabel lblSelected = new JLabel("Selected");
		lblSelected.setBounds(351, 79, 70, 15);
		panel.add(lblSelected);


		JLabel lblName_1 = new JLabel("Name :");
		lblName_1.setBounds(12, 12, 102, 15);
		panel.add(lblName_1);
		
		JLabel lblDepartment = new JLabel("Department :");
		lblDepartment.setBounds(12, 38, 102, 15);
		panel.add(lblDepartment);
		
		JLabel lblRollNo = new JLabel("Roll NO.:");
		lblRollNo.setBounds(286, 12, 70, 15);
		panel.add(lblRollNo);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(286, 38, 70, 15);
		panel.add(lblYear);
		
		JLabel lblmName = new JLabel("mName");
		lblmName.setBounds(126, 11, 148, 16);
		
		panel.add(lblmName);
		
		JLabel lblmDept = new JLabel("mDept");
		lblmDept.setBounds(126, 37, 148, 16);
		panel.add(lblmDept);
		
		JLabel lblmRollNO = new JLabel("New label");
		lblmRollNO.setBounds(368, 11, 176, 16);
		panel.add(lblmRollNO);
		
		JLabel lblmYear = new JLabel("New label");
		lblmYear.setBounds(368, 37, 145, 16);
		panel.add(lblmYear);
		
ActionListener actionListener =new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b=(JButton) e.getSource();
				
//				frame.setVisible(false);
				switch(b.getText()){
				case "Submit":{
					
				}
					lblMessage.setText("Submited");
					break;
				case "Reset":
					lblMessage.setText("Reseted");
					break;
				case "Reload":
					lblMessage.setText("Reloaded");
					break;
				case ">>>":{
					int i=list.getSelectedIndex();
					list2.add(list1.get(i));
					list1.remove(i);
					list.setListData(toStringArray(list1));
					list_1.setListData(toStringArray(list2));
					lblMessage.setText("inserted");
				}
				break;
				case "<<<":{
					int i=list_1.getSelectedIndex();
					list1.add(list2.get(i));
					list2.remove(i);
					list.setListData(toStringArray(list1));
					list_1.setListData(toStringArray(list2));
					lblMessage.setText("removed");
				}
				break;
				default:
				}
//				frame.setVisible(true);			
			}
		};


		buttonAdd.addActionListener(actionListener);
		btnDelete.addActionListener(actionListener);
		btnReload.addActionListener(actionListener);
		btnReset.addActionListener(actionListener);
		btnSubmit.addActionListener(actionListener);
		return panel;
	}
	public static String[] toStringArray(ArrayList<Course> list1){
		String list []=new String[list1.size()];
		for (int i = 0; i < list1.size(); i++) {
			list[i]=list1.get(i).getName();
		}
		return list;
	}
}



