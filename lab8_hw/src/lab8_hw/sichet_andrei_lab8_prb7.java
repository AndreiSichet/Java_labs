package lab8_hw;
import java.io.*;
import java.util.*;
class IntArray implements Serializable{
	private static final long serialVersionUID = 1L;
	int [] values;
	public IntArray(int[] values) {
        this.values = values;
    }
    public int[] getValues() {
        return values;
    }
}
public class sichet_andrei_lab8_prb7 {
	public static void main(String[] args) {
		try {
			Scanner in=new Scanner(System.in);
			System.out.println("Enter number of elements: ");
			int n=in.nextInt();
			int []arr=new int[n];
			for(int i=0;i<n;i++) {
				System.out.println("Enter elements "+(i+1));
				arr[i]=in.nextInt();
			}
			Arrays.sort(arr);
			IntArray obj = new IntArray(arr);
			ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("array.ser"));
			oos.writeObject(obj);
			oos.close();
			System.out.println("Object serialized.");
			ObjectInputStream ois =new ObjectInputStream(new FileInputStream("array.ser"));
			IntArray restored=(IntArray)ois.readObject();
			ois.close();
			System.out.println("Deserialized array:");
			for (int value : restored.getValues()) {
                System.out.print(value + " ");
            }
			in.close();
		}catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

	}

}
