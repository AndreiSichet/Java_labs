package lab8_hw;
import java.io.*;
public class sichet_andrei_lab8_prb6 {
	public static void main(String[] args)  {
		try {
			BufferedReader file=new BufferedReader (new FileReader ("people.csv"));
			BufferedWriter nameWriter=new BufferedWriter(new FileWriter("names.txt"));
			BufferedWriter surnameWriter=new BufferedWriter(new FileWriter("surnames.txt"));
			BufferedWriter phoneWriter=new BufferedWriter(new FileWriter("phones.txt"));
			BufferedWriter decemberWriter=new BufferedWriter(new FileWriter("birth_dates.txt"));
			BufferedWriter linkWriter=new BufferedWriter(new FileWriter("Facebook_links.txt"));
			String line;
			while((line=file.readLine())!=null) {
				String[] parts = line.split("/");
				String name=parts[0];
				String surname=parts[1];
				String phone=parts[2];
				String date=parts[3];
				String facebook=parts[4];
				String[] dateParts=date.split("-");
				if(dateParts[1].equals("12")) {
					decemberWriter.write(line);
					decemberWriter.newLine();
				}
				if(surname.equals("Andrei")||name.equals("Nicolae")) {
					surnameWriter.write(line);
					surnameWriter.newLine();
				}
				if(phone.startsWith("+40")) {
					phoneWriter.write(line);
					phoneWriter.newLine();
				}
				if(facebook.matches(".*\\d+$")) {
					linkWriter.write(line);
                    linkWriter.newLine();
				}
				file.close();
	            decemberWriter.close();
	            phoneWriter.close();
	            nameWriter.close();
	            linkWriter.close();
	            surnameWriter.close();
	            System.out.println("Files generated successfully.");
			}
			
		}catch(IOException e) {
			System.out.println("Error: "+e.getMessage());
		}
		

	}

}
