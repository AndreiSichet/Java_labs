package lab9_hw;
import java.io.*;
class SharedFile {
    private final String fileName = "data.txt";
    private boolean written = false;
    public synchronized void writeData(String data) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(data);
            fw.close();
            written = true;
            System.out.println("Written: " + data);
            notify(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void readData() {
        while (!written) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            System.out.println("Reading file content:");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class WriterThread extends Thread {
    private SharedFile sharedFile;
    public WriterThread(SharedFile sharedFile) {
        this.sharedFile = sharedFile;
    }
    @Override
    public void run() {
        sharedFile.writeData("I like pizza with pineapple, do something about it!");
    }
}
class ReaderThread extends Thread {
    private SharedFile sharedFile;
    public ReaderThread(SharedFile sharedFile) {
        this.sharedFile = sharedFile;
    }
    @Override
    public void run() {
        sharedFile.readData();
    }
}

public class sichet_andrei_lab9_prb6{
    public static void main(String[] args) {
        SharedFile sharedFile = new SharedFile();
        Thread reader = new ReaderThread(sharedFile);
        Thread writer = new WriterThread(sharedFile);
        reader.start();
        writer.start();
    }
}