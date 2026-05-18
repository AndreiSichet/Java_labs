package lab9_hw;
import java.util.Scanner;
class GCDThread extends Thread {
    private long a;
    private long b;
    public GCDThread(long a, long b) {
        this.a = a;
        this.b = b;
    }
    private long gcd(long x, long y) {
        while (y != 0) {
            long temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
    @Override
    public void run() {
        long result = gcd(a, b);
        System.out.println(
                "GCD(" + a + ", " + b + ") = " + result
        );
    }
}
public class sichet_andrei_lab9_prb5{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter two numbers:");
            long a = scanner.nextLong();
            long b = scanner.nextLong();
            GCDThread thread = new GCDThread(a, b);
            thread.start();
        }
    }
}