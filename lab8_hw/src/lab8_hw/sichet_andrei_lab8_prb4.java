package lab8_hw;
import java.io.*;
public class sichet_andrei_lab8_prb4 {
	public static void main(String[] args) {
		String inputFile="input.bin";
		String outputFile="output.bin";
		try {
			FileInputStream fis=new FileInputStream(inputFile);
			byte[] privateKey=new byte[256];
			int readBytes=fis.read(privateKey);
			fis.close();
			if (readBytes != 256) {
                System.out.println("Error: Private key must contain exactly 256 bytes.");
                return;
            }
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter public key: ");
            String publicKey = br.readLine();
            byte[] publicBytes = publicKey.getBytes();
            byte[] transactionId = new byte[256];
            for (int i = 0; i < 256; i++) {
                byte pubByte = (i < publicBytes.length) ? publicBytes[i] : 0;
                transactionId[i] = (byte) (privateKey[i] ^ pubByte);
            }
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(transactionId);
            fos.close();
            System.out.println("Transaction ID generated successfully.");
            }catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
		}

	}
