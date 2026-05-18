package lab9_hw;
class MyRunnable implements Runnable {
    private String name;
    private static int counter = 0;
    public MyRunnable(String name) {
        this.name = name;
        counter++;
    }
    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            System.out.println(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class sichet_andrei_lab9_prb1{
    public static void main(String[] args) {
        MyRunnable obj1 = new MyRunnable("Run 1");
        MyRunnable obj2 = new MyRunnable("Run 2");
        MyRunnable obj3 = new MyRunnable("Run 3");
        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);
        Thread t3 = new Thread(obj3);
        t1.start();
        t2.start();
        t3.start();
    }
}