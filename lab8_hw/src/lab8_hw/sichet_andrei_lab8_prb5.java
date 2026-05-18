package lab8_hw;
import java.io.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
public class sichet_andrei_lab8_prb5 {
	public static void main(String[] args) throws IOException{
		File folder=new File("students");
		File[] files=folder.listFiles();
		Vector<InputStream> streams=new Vector<>();
		for(File file:files) {
			if(file.isFile()&&file.getName().startsWith("Year_")&&file.getName().endsWith(".txt")) {
				streams.add(new FileInputStream(file));
			}
		}
		SequenceInputStream sis = new SequenceInputStream(streams.elements());
		BufferedReader br=new BufferedReader(new InputStreamReader(sis));
		ArrayList<String> students=new ArrayList<>();
		String line;
		while((line=br.readLine())!=null) {
			students.add(line);
		}
		br.close();
		Collections.sort(students);
		BufferedWriter bw=new BufferedWriter(new FileWriter("all_students.txt"));
		for(String student:students) {
			bw.write(student);
			bw.newLine();
		}
		bw.close();
		System.out.println("Students sorted");
	}

}
