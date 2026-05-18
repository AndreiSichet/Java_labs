package lab7_hw;
import java.util.*;
class UserFile{
	String name;
	String extension;
	int type;
	int size;
	public UserFile(String name, String extension, int type, int size) {
		this.name=name;
		this.extension=extension;
		this.type=type;
		this.size=size;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setExtension(String extension) {
		this.extension=extension;
	}
	public void setType(int type) {
		this.type=type;
	}
	public void setSize(int size) {
		this.size=size;
	}
	public String getName() {
		return name;
	}
	public String getExtension() {
		return extension;
	}
	public int getType() {
		return type;
	}
	public int getSize() {
		return size;
	}
}
class UserFileSizeComparator implements Comparator<UserFile> {

    @Override
    public int compare(UserFile f1, UserFile f2) {
        return f1.getSize() - f2.getSize(); 
    }
}
public class sichet_andrei_lab7_prb4 {

	public static void main(String[] args) {
		Hashtable<Integer, String> types = new Hashtable<>();
		types.put(1, "image");
		types.put(2, "text");
		types.put(3, "application");
		Scanner in=new Scanner(System.in);
		ArrayList<UserFile> files = new ArrayList<>();
		System.out.println("Enter number of user files: ");
		int n=in.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println("Enter info for file "+(i+1));
			System.out.print("Name: ");
            String name = in.next();
            System.out.print("Extension: ");
            String extension = in.next();
            System.out.print("Type (1=image,2=text,3=application): ");
            int type = in.nextInt();
            if (!types.containsKey(type)) {
                System.out.println("Invalid type, file ignored");
                i--;
                continue;
            }
            System.out.print("Size: ");
            int size = in.nextInt();
            UserFile file = new UserFile(name, extension, type, size);
            files.add(file);
		}
		files.sort(new UserFileSizeComparator());
		System.out.println("\nFiles sorted by size:");
        for (UserFile f : files) {
            System.out.println(f.getName() + "." + f.getExtension() +" | type=" + types.get(f.getType()) +" | size=" + f.getSize());
        }
        in.close();
	}

}
