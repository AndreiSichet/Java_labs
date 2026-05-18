package lab7_hw;
import java.util.*;
class Person{
	protected String name;
	protected int age; 
}
class Kid extends Person{	
}
class Adult extends Person{
}
class Retired extends Person{
}
class SetterGetter<T extends Person>{
	public void setName(T obj, String name) {
		obj.name=name;
	}
	public void setAge(T obj, int age) {
		obj.age=age;
	}
	public String getName(T obj) {
		return obj.name;
	}
	public int getAge(T obj) {
		return obj.age;
	}
}
public class sichet_andrei_lab7_prb3 {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.println("Enter name and age for kid");
		System.out.println("Name: ");
		String name=in.nextLine();
		System.out.println("Age: ");
		int age=in.nextInt();
		Kid kid =new Kid();
		SetterGetter<Kid> k=new SetterGetter();
		k.setAge(kid, age);
		k.setName(kid, name);
		System.out.println("Enter name and age for adult");
		System.out.println("Name: ");
		String name1=in.nextLine();
		System.out.println("Age: ");
		int age1=in.nextInt();
		Adult adult =new Adult();
		SetterGetter<Adult> a=new SetterGetter();
		a.setAge(adult, age1);
		a.setName(adult, name1);
		System.out.println("Enter name and age for kid");
		System.out.println("Name: ");
		String name2=in.nextLine();
		System.out.println("Age: ");
		int age2=in.nextInt();
		Retired retired =new Retired();
		SetterGetter<Retired> r=new SetterGetter();
		r.setAge(retired, age2);
		r.setName(retired, name2);
		System.out.println("Kid info: "+k.getName(kid)+" "+k.getAge(kid));
		System.out.println("Adult info: "+a.getName(adult)+" "+a.getAge(adult));
		System.out.println("Retired info: "+r.getName(retired)+" "+r.getAge(retired));
	}

}
