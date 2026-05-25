package lab7_hw;
import java.util.*;
class Employee{
	String name;
	int age;
	int salary;
	public void setName(String name) {
		this.name=name;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setSalary(int salary) {
		this.salary=salary;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public int getSalary() {
		return salary;
	}
}
class NameComparator implements Comparator<Employee>{
	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.getName().compareTo(e2.getName());
	}
}
class AscNameDscSalary implements Comparator<Employee>{
	@Override
	public int compare(Employee e1, Employee e2) {
		if(e1.getName().compareTo(e2.getName())!=0) {
			return e1.getName().compareTo(e2.getName());
		}else {
			return -1*Integer.compare(e1.getSalary(), e2.getSalary());
		}
	}
}
public class sichet_andrei_lab7_prb6 {

	public static void main(String[] args) {
		ArrayList<Employee> list = new ArrayList<>();
		Scanner in=new Scanner(System.in);
		System.out.println("Enter number of employees: ");
		int n=in.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println("Enter data for employee "+(i+1));
			System.out.println("Name: ");
			String name=in.nextLine();
			System.out.println("Age: ");
			int age=in.nextInt();
			System.out.println("Salary: ");
			int salary=in.nextInt();
			Employee e=new Employee();
			e.setName(name);
			e.setAge(age);
			e.setSalary(salary);
			list.add(e);
		}
		System.out.println("Employees sorted ascending by name: ");
		list.sort(new NameComparator());
		int i=1;
		for(Employee a:list) {
			System.out.println("Employee number "+i+" data: ");
			System.out.println("Name: "+a.getName());
			System.out.println("Age: "+a.getAge());
			System.out.println("Salary: "+a.getSalary());
			i++;
		}
		i=1;
		System.out.println("Employees sorted descending by age: ");
		list.sort((e1, e2) -> Integer.compare(e2.getAge(), e1.getAge()));
		for(Employee a:list) {
			System.out.println("Employee number "+i+" data: ");
			System.out.println("Name: "+a.getName());
			System.out.println("Age: "+a.getAge());
			System.out.println("Salary: "+a.getSalary());
			i++;
		}
		i=1;
		System.out.println("Employees sorted ascending by name and descending by salary: ");
		list.sort(new AscNameDscSalary());
		for(Employee a:list) {
			System.out.println("Employee number "+i+" data: ");
			System.out.println("Name: "+a.getName());
			System.out.println("Age: "+a.getAge());
			System.out.println("Salary: "+a.getSalary());
			i++;
		}
		in.close();

	}

}
