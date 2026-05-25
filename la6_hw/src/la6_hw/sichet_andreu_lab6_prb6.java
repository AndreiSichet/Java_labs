package la6_hw;
import java.util.Random;
class OverSaturatedException extends Exception {
    public OverSaturatedException(String msg) {
        super(msg);
    }
}
public class sichet_andreu_lab6_prb6 {
	public static void generateColor() throws OverSaturatedException{
		Random rand= new Random();
		int attempts=0;
		while(attempts<10) {
			int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            
            float rn = r / 255f;
            float gn = g / 255f;
            float bn = b / 255f;
            
            float max = Math.max(rn, Math.max(gn, bn));
            float min = Math.min(rn, Math.min(gn, bn));
            float delta = max - min;
            float saturation;
            if (max == 0) {
                saturation = 0;
            } else {
                saturation = delta / max;
            }
            saturation *= 100;
            assert saturation <= 90 : "Over saturation invariant violated";
            if (saturation <= 90) {
                System.out.println("Valid RGB: " + r + ", " + g + ", " + b);
                System.out.println("Saturation: " + saturation);
                return;
            }
            attempts++;

		}
		throw new OverSaturatedException("Failed after 10 attempts");
	}
	public static void main(String[] args) {
		try {
            generateColor();
        } catch (OverSaturatedException e) {
            System.out.println("Exception: " + e.getMessage());
        }

	}

}
