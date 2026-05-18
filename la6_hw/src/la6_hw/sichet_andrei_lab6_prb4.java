package la6_hw;
class SuperException extends Exception{
	public SuperException() {
		System.out.println("SuperException triggered");
	}
}
class SmallerException extends SuperException{
	public SmallerException() {
		System.out.println("SmallerException triggered");
	}
}
public class sichet_andrei_lab6_prb4 {
static void a() throws SmallerException {
	throw new SmallerException();
}
static void b() throws SuperException{
	throw new SuperException();
}
	public static void main(String[] args) {
		try {
			a();
		}catch(SmallerException e) {
			System.out.println("SmallerException in method a()");
		}catch(SuperException e) {
			System.out.println("SuperException in method a()");
		}
		try {
			b();
		}catch(SmallerException e) {
			System.out.println("SmallerException in method b()");
		}catch(SuperException e) {
			System.out.println("SuperException in method b()");
		}
	}

}
