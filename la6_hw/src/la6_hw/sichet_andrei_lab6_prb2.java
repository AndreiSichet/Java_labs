package la6_hw;

public class sichet_andrei_lab6_prb2 {

	public static void main(String[] args) {
		try {
			int a=5;
			int b=0;
			int r=a/b;
			int[]arr=new int[3];
			System.out.println(arr[3]);
			String s=null;
			System.out.println(s.length());
			String num="jsj";
			int n=Integer.parseInt(num);
		}catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());

        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Other Exception: " + e.getMessage());

        } finally {
            System.out.println("I may or may not have caught an exception");
        }

	}

}
