package lab9_hw;
class Counter {
    private int value = 0;
    public synchronized void increment() {
        value++;
    }
    public synchronized void decrement() {
        value--;
    }
    public int getValue() {
        return value;
    }
}
class IncrementThread extends Thread {
    private Counter counter;
    public IncrementThread(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
    }
}
class DecrementThread extends Thread {
    private Counter counter;
    public DecrementThread(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.decrement();
        }
    }
}
public class sichet_andrei_lab9_prb4{
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new IncrementThread(counter);
        Thread t2 = new IncrementThread(counter);
        Thread t3 = new DecrementThread(counter);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Final value: " + counter.getValue());
    }
}