package lab9_hw;
import java.util.Random;
class NumberGenerator extends Thread {
    private int[] numbers;
    public NumberGenerator(int[] numbers) {
        this.numbers = numbers;
    }
    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            numbers[i] = random.nextInt(31); 
            System.out.println("Generated number: " + numbers[i]);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class CircleAreaCalculator extends Thread {
    private int[] numbers;
    public CircleAreaCalculator(int[] numbers) {
        this.numbers = numbers;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int number : numbers) {
            if (number % 3 == 0) {
                double area = Math.PI * number * number;
                System.out.println(
                        "Radius: " + number +
                        " -> Area: " + area
                );
            }
        }
    }
}
public class sichet_andrei_lab9_prb2{
    public static void main(String[] args) {
        int[] numbers = new int[30];
        NumberGenerator generator = new NumberGenerator(numbers);
        CircleAreaCalculator calculator = new CircleAreaCalculator(numbers);
        generator.start();
        calculator.start();
    }
}