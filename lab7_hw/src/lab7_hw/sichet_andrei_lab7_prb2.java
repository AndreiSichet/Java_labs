package lab7_hw;
import java.util.*;
class Calculator<T>{
	public T add(T a, T b) {
		if(a instanceof Integer && b instanceof Integer) {
			Integer x=(Integer) a;
			Integer y=(Integer) b;
			Integer rez=x+y;
			return (T)rez;
		}else if(a instanceof Double && b instanceof Double) {
			Double x=(Double) a;
			Double y=(Double) b;
			Double rez=x+y;
			return (T)rez;
		}else if(a instanceof String && b instanceof String) {
			String x=(String) a;
			String y=(String) b;
			String rez=x+y;
			return (T)rez;
		}
		return null;
	}
	public T subtract(T a, T b) {
		if(a instanceof Integer && b instanceof Integer) {
			Integer x=(Integer) a;
			Integer y=(Integer) b;
			Integer rez=x-y;
			return (T)rez;
		}else if(a instanceof Double && b instanceof Double) {
			Double x=(Double) a;
			Double y=(Double) b;
			Double rez=x-y;
			return (T)rez;
		}else if(a instanceof String && b instanceof String) {
			String x=(String) a;
			String y=(String) b;
			String rez=x.replaceFirst(y, "");
			return (T)rez;
		}
		return null;
	}
	public T multiply(T a, T b) {
		if(a instanceof Integer && b instanceof Integer) {
			Integer x=(Integer) a;
			Integer y=(Integer) b;
			Integer rez=x*y;
			return (T)rez;
		}else if(a instanceof Double && b instanceof Double) {
			Double x=(Double) a;
			Double y=(Double) b;
			Double rez=x*y;
			return (T)rez;
		}else {
			System.out.println("Operation not allowed for String");
			return null;	
		}
	}
	public T divide(T a, T b) {
		if(a instanceof Integer && b instanceof Integer) {
			Integer x=(Integer) a;
			Integer y=(Integer) b;
			if(y!=0) {
			Integer rez=x/y;
			return (T)rez;
			}else {
				System.out.println("Cannot divide with 0");
				return null;
			}
		}else if(a instanceof Double && b instanceof Double) {
			Double x=(Double) a;
			Double y=(Double) b;
			if(y!=0.0) {
			Double rez=x/y;
			return (T)rez;
			}else {
				System.out.println("cannot divide with 0");
				return null;
			}
		}else {
			System.out.println("Operation not allowed for String");
			return null;	
		}
	}
}
public class sichet_andrei_lab7_prb2 {
	public static void main(String[] args) {
		// Integer
        Calculator<Integer> calcInt = new Calculator();
        System.out.println("Integer add: " + calcInt.add(5, 3));
        System.out.println("Integer subtract: " + calcInt.subtract(10, 4));
        System.out.println("Integer multiply: " + calcInt.multiply(2, 6));
        System.out.println("Integer divide: " + calcInt.divide(10, 2));
        System.out.println("Integer divide by 0: " + calcInt.divide(5, 0));

        System.out.println();

        // Double
        Calculator<Double> calcDouble = new Calculator();
        System.out.println("Double add: " + calcDouble.add(5.5, 2.3));
        System.out.println("Double subtract: " + calcDouble.subtract(10.5, 4.2));
        System.out.println("Double multiply: " + calcDouble.multiply(2.5, 3.0));
        System.out.println("Double divide: " + calcDouble.divide(10.0, 2.0));
        System.out.println("Double divide by 0: " + calcDouble.divide(10.0, 0.0));

        System.out.println();

        // String
        Calculator<String> calcString = new Calculator();
        System.out.println("String add: " + calcString.add("Hello ", "World"));
        System.out.println("String subtract: " + calcString.subtract("banana", "na"));
        System.out.println("String multiply: " + calcString.multiply("a", "b"));
        System.out.println("String divide: " + calcString.divide("a", "b"));

	}

}
