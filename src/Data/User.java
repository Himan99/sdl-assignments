package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7680014466797872891L;
	private String name;
	private String rollno;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getDiv() {
		return div;
	}
	public void setDiv(int div) {
		this.div = div;
	}
	private String dept;
	int div;
	private PriorityQueue<Course> priorityQueue;
	public User(String name, String rollno) {
		this.name = name;
		this.rollno = rollno;
		div =Integer.valueOf(""+rollno.charAt(1));
		if(div>=1 &&div<=4){
			dept="Comp";
		}else if(div>=5&&div<=8){
			dept="IT";
		}else{
			dept="EnTC";
		}
		priorityQueue=new PriorityQueue<Course>(new CourseComparator());
	}
	public PriorityQueue<Course> getPriorityQueue() {
		return priorityQueue;
	}
	public void setPriorityQueue(ArrayList<Course> listOfCourses) {
		for (int i = 0; i < listOfCourses.size(); i++) {
			int p;
			Course course= listOfCourses.get(i);
			String string = course.getName();
			System.out.println("Set priority for "+string);
			p=Utility.sc.nextInt();
			course.setPriority(p);
			priorityQueue.add(course);
		}
		if(priorityQueue.peek()!=null)
		System.out.println(priorityQueue.peek().getName());
	}
//	public void setPriorityQueue(PriorityQueue<Course> p) {
//		this.priorityQueue=p;
//	}
	public void display(){
		System.out.println(name);
		System.out.println(rollno);
		if(priorityQueue.peek()!=null)
		System.out.println("first preferrence is "+priorityQueue.peek().getName());
	}
	
}

