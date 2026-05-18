package lab9_hw;
class Fibonacci {
    private int value;
    private boolean ready = false;
    public synchronized void calculate(int n) {
        int a = 0;
        int b = 1;
        if (n == 0) {
            value = 0;
        } else if (n == 1) {
            value = 1;
        } else {
            for (int i = 2; i <= n; i++) {
                int c = a + b;
                a = b;
                b = c;
            }
            value = b;
        }
        System.out.println("Desired Fibonacci value: " + value);
        ready = true;
        notify();
    }
    public synchronized void display() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int a = 0;
        int b = 1;
        System.out.println("Fibonacci numbers smaller than " + value + ":");
        while (a < value) {
            System.out.print(a + " ");
            int c = a + b;
            a = b;
            b = c;
        }
    }
}
public class sichet_andrei_lab9_prb3{
    public static void main(String[] args) {
    	   Fibonacci fibonacci = new Fibonacci();
           Thread t1 = new Thread(() -> fibonacci.calculate(10));
           Thread t2 = new Thread(() -> fibonacci.display());
           t2.start();
           t1.start();
    }
}