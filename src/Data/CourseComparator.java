package Data;

import java.io.Serializable;
import java.util.Comparator;

public class CourseComparator implements Serializable ,Comparator<Course>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 515582518639009981L;

	public int compare(Course o1, Course o2) {
		// TODO Auto-generated method stub
		if(o1.getPriority()<o2.getPriority())
			return 1;

		if(o1.getPriority()>o2.getPriority())
			return -1;
		
		return 0;
	}

}
