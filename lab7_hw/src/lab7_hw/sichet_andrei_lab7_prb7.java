package lab7_hw;
import java.util.*;
class Publication{
	protected String title;
	protected String author;
	public Publication(String title, String author) {
		this.title=title;
		this.author=author;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
}
class Book extends Publication{
	public Book(String title, String author) {
		super(title,author);
	}
}
class Article extends Publication{
	public Article(String title, String author) {
		super(title,author);
	}
}
class MediaResource extends Publication{
	public MediaResource(String title, String author) {
		super(title,author);
	}
}
class Magazine extends Publication{
	public Magazine(String title, String author) {
		super(title,author);
	}
}
class Manual extends Publication{
	public Manual(String title, String author) {
		super(title,author);
	}
}
class PublicationComparator implements Comparator<Publication>{
	@Override
	public int compare(Publication a, Publication b) {
		if(a.getTitle().compareTo(b.getTitle())!=0) {
			return a.getTitle().compareTo(b.getTitle());
		}else {
			return a.getAuthor().compareTo(b.getAuthor());
		}
	}
}
class VirtualLibrary<T extends Publication> {
    private SortedSet<T> entries;
    public VirtualLibrary(Comparator<T> comparator) {
        this.entries = new TreeSet<>(comparator);
    }
    public void add(T entry) {
        entries.add(entry);
    }
    public void addAll(Collection<T> list) {
        entries.addAll(list);
    }
    public boolean exists(T entry) {
        return entries.contains(entry);
    }
    public SortedSet<T> getAll() {
        return entries;
    }
    public T getByTitle(String title) {
        for (T e : entries) {
            if (e.getTitle().equals(title)) {
                return e;
            }
        }
        return null;
    }
}
public class sichet_andrei_lab7_prb7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        VirtualLibrary<Publication> lib =new VirtualLibrary<>(new PublicationComparator());
        System.out.print("Number of publications: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Publication " + (i + 1));
            System.out.print("Type (1=Book,2=Article,3=Media,4=Magazine,5=Manual): ");
            int type = sc.nextInt();
            System.out.print("Title: ");
            String title = sc.next();
            System.out.print("Author: ");
            String author = sc.next();
            Publication p;
            switch (type) {
                case 1:
                    p = new Book(title, author);
                    break;
                case 2:
                    p = new Article(title, author);
                    break;
                case 3:
                    p = new MediaResource(title, author);
                    break;
                case 4:
                    p = new Magazine(title, author);
                    break;
                case 5:
                    p = new Manual(title, author);
                    break;
                default:
                    System.out.println("Invalid type, skipped!");
                    i--;
                    continue;
            }
            lib.add(p);
        }
        System.out.println("\nALL PUBLICATIONS (sorted): ");
        for (Publication p : lib.getAll()) {
            System.out.println(p.getTitle() + " | " + p.getAuthor());
        }
        System.out.println("\nCHECK EXISTS");
        System.out.print("Title to search: ");
        String search = sc.next();
        Publication found = lib.getByTitle(search);
        if (found != null) {
            System.out.println("Found: " + found.getTitle() + " | " + found.getAuthor());
        } else {
            System.out.println("Not found");
        }
        System.out.println("\nADD MULTIPLE");
        List<Publication> extra = new ArrayList<>();
        extra.add(new Book("Java2", "AuthorA"));
        extra.add(new Article("AI2", "AuthorB"));
        lib.addAll(extra);
        System.out.println("\nAfter addAll:");
        for (Publication p : lib.getAll()) {
            System.out.println(p.getTitle() + " | " + p.getAuthor());
        }
        sc.close();

	}

}
