package lab8_hw;
import java.io.*;
public class sichet_andrei_lab8_prb1  {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StreamTokenizer st = new StreamTokenizer(br);
		 while (true) {
	            int tokenType = st.nextToken();
	            if (tokenType == StreamTokenizer.TT_EOL) {
	                break;
	            }
	            if (tokenType == StreamTokenizer.TT_WORD) {
	                String word = st.sval;
	                if (word.equals("STOP")) {
	                    break;
	                }
	                System.out.println(word);
	            }
	            else if (tokenType == StreamTokenizer.TT_NUMBER) {
	                double number = st.nval;
	                if (number == (int) number) {
	                    System.out.println((int) number);
	                } else {
	                    System.out.println(number);
	                }
	            }
	        }
	}

}
