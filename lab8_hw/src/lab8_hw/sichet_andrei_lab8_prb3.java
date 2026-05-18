package lab8_hw;
import java.io.*;
import java.util.HashMap;
public class sichet_andrei_lab8_prb3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, String> months = new HashMap<>();
		months.put(1, "January");
		months.put(2, "February");
		months.put(3, "March");
		months.put(4, "April");
		months.put(5, "May");
		months.put(6, "June");
		months.put(7, "July");
		months.put(8, "August");
		months.put(9, "September");
		months.put(10, "October");
		months.put(11, "November");
		months.put(12, "December");
		try {
			while(true) {
				String input = br.readLine();
				if(input.equals("X")||input.equals("x")) {
					break;
				}
				String[] parts = input.split("/");
				int day = Integer.parseInt(parts[0]);
				int month = Integer.parseInt(parts[1]);
				int year = Integer.parseInt(parts[2]);
				String monthName=months.get(month);
				System.out.println(day+" "+monthName+" "+year);
				if(year%400==0||((year%4==0)&&(year%100!=0))) {
					System.out.println("This is a leap year");
				}else {
					System.out.println("This is not a leap year");
				}
			}
		}catch(IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
	}

}
