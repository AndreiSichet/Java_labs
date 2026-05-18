package lab7_hw;
import java.util.*;
class Student{
	String name;
	int group;
	double average_mark;
public Student(String name,int group, double avg) {
	this.name=name;
	this.group=group;
	average_mark=avg;
}
public void setName(String name) {
	this.name=name;
}
public void setGroup(int g) {
	group=g;
}
public void setAvg(double avg) {
	average_mark=avg;
}
public String getName() {
	return name;
}
public int getGroup() {
	return group;
}
public double getAvg() {
	return average_mark;
}
}
class SpecialComparator implements Comparator<Student>{
	@Override
	public int compare(Student s1, Student s2) {
        if (s1.getAvg() != s2.getAvg()) {
            return Double.compare(s2.getAvg(), s1.getAvg());
        } else {
            return s1.getName().compareTo(s2.getName());
        }
    }
}
public class sichet_andrei_lab7_prb5 {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
	    SortedSet<Student> students = new TreeSet<>(new SpecialComparator());
	    System.out.println("Enter number of students: ");
	    int n=in.nextInt();
	    for(int i=0;i<n;i++) {
	    	System.out.println("Student " + (i + 1));
            System.out.print("Name: ");
            String name = in.next();
            System.out.print("Group: ");
            int group = in.nextInt();
            System.out.print("Average mark: ");
            double mark = in.nextDouble();
            students.add(new Student(name, group, mark));
	    }
	    System.out.println("All students with for loop: ");
	    for(Student s:students) {
	    	System.out.println(s.getName() + " | " + s.getGroup() + " | " + s.getAvg());
	    }
	    System.out.println("All students with iterator: ");
	    Iterator<Student> it = students.iterator();
	    while (it.hasNext()) {
            Student s = it.next();
            if (s.getAvg() >= 8) {
                System.out.println(s.getName() + " | " + s.getGroup() + " | " + s.getAvg());
            }
        }
	    System.out.println("Enter group to filter: ");
	    int a=in.nextInt();
	    System.out.println("Students by group: ");
	    students.forEach(s -> {
            if (s.getGroup()==a) {
                System.out.println(s.getName() + " | " + s.getGroup() + " | " + s.getAvg());
            }
        });
	    in.close();
	}

}
