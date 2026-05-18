package la6_hw;
import java.util.Scanner;
class AcuteTriangle extends Exception {
    public AcuteTriangle() {
        super("Acute triangle detected");
    }
}
class RightTriangle extends Exception {
    public RightTriangle() {
        super("Right triangle detected");
    }
}
class ImpossibleTriangle extends Exception {
    public ImpossibleTriangle() {
        super("Impossible triangle");
    }
}
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public double distance(Point other) {
		double dist=Math.sqrt((x-other.x)*(x-other.x)+(y-other.y)*(y-other.y));
		return dist;
	}
}
public class sichet_andrei_lab6_prb5 {

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the coordinates for the first point: ");
		int x1=in.nextInt();
		int y1=in.nextInt();
		Point a=new Point(x1,y1);
		System.out.println("Enter the coordinates for the second point: ");
		int x2=in.nextInt();
		int y2=in.nextInt();
		Point b=new Point(x2,y2);
		System.out.println("Enter the coordinates for the third point: ");
		int x3=in.nextInt();
		int y3=in.nextInt();
		Point c=new Point(x3,y3);
		System.out.println("The distance ab= "+ a.distance(b));
		System.out.println("The distance ac= "+ a.distance(c));
		System.out.println("The distance bc= "+ b.distance(c));
		double sideA=a.distance(b);
		double sideB=a.distance(c);
		double sideC=b.distance(c);
		 try {
	            if (sideA + sideB <= sideC ||sideA + sideC <= sideB ||sideB + sideC <= sideA) {
	                throw new ImpossibleTriangle();
	            }

	            double cosA = (sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC);
	            double cosB = (sideA * sideA + sideC * sideC - sideB * sideB) / (2 * sideA * sideC);
	            double cosC = (sideA * sideA + sideB * sideB - sideC * sideC) / (2 * sideA * sideB);

	            if (cosA < 0 || cosB < 0 || cosC < 0) {
	                System.out.println("Obtuse triangle");
	            }
	            else if (Math.abs(cosA) < 1e-9 ||Math.abs(cosB) < 1e-9 ||Math.abs(cosC) < 1e-9) {
	                throw new RightTriangle();
	            }
	            else {
	                throw new AcuteTriangle();
	            }
	        }
	        catch (ImpossibleTriangle e) {
	            System.out.println("Warning: " + e.getMessage());
	            throw new RuntimeException(e);
	        }
	        catch (AcuteTriangle e) {
	            System.out.println(e.getMessage());
	        }
	        catch (RightTriangle e) {
	            System.out.println(e.getMessage());
	        }
	}

}
