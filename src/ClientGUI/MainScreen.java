package ClientGUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  

import Data.Course;

public class MainScreen {
	static JFrame f;
	public static void main(String[] args) {
		f=new JFrame();
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
		JTextField mUsernameTF=new JTextField("Username");
		JPasswordField mPasswordTF=new JPasswordField();
		mUsernameTF.setBounds(100, 100, 200, 50);
		mPasswordTF.setBounds(100, 150, 200, 50);
		mLoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.getContentPane().removeAll();
				f.getContentPane().add(getSelectorPanel());
				f.setSize(400, 400);
				f.setLayout(null);
				f.setVisible(true);
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
		JTextField mUsernameTF=new JTextField("Username");
		JTextField mNameTF=new JTextField("Name");
		JTextField mRollNoTF=new JTextField("Roll Number");
		JPasswordField mPasswordTF=new JPasswordField();
		mUsernameTF.setBounds(100, 0, 200, 50);
		mNameTF.setBounds(100, 50, 200, 50);
		mRollNoTF.setBounds(100, 100, 200, 50);
		mPasswordTF.setBounds(100, 150, 200, 50);
		mLoginButton.setBounds(150, 250, 100, 50);
		mLoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		JPanel f1=new JPanel();
		JButton insertButton=new JButton(">>");
		JButton deleteButton=new JButton("<<");
		JButton loadButton=new JButton("load courses");
		JButton submitButton=new JButton("Submit");
		JList list1=new JList<Course>();
		JList list2=new JList<Course>();
		loadButton.setBounds(10,100,150,30);
		submitButton.setBounds(220,100,150,30);
		insertButton.setBounds(175, 150, 50, 50);
		deleteButton.setBounds(175, 200, 50, 50);
		list1.setBounds(10,140,140,120);
		list2.setBounds(250,140,140,120);
		f1.add(loadButton);
		f1.add(submitButton);
		f1.add(deleteButton);
		f1.add(list1);
		f1.add(list2);
		f1.setSize(400, 400);
		f1.setLayout(null);
		return f1;
	}
}



