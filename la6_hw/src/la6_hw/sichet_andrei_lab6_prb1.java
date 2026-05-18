package la6_hw;
import java.util.Scanner;
public class sichet_andrei_lab6_prb1 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of elements");
		int n=sc.nextInt();
		double[] arr=new double[n];
		System.out.println("Enter "+n+" elements");
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextDouble();
		}
		try {
			System.out.println("Enter index to access");
			int index=sc.nextInt();
			double value=arr[index];
			System.out.println("Value at index "+index+ " is "+value);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: Index is out of bounds! Valid range is 0 to " + (n - 1));
		}
		System.out.println("Enter rows and columns");
		int rows=sc.nextInt();
		int col=sc.nextInt();
		System.out.println("Enter elements of matrix");
		int [][] matrix=new int[rows][col];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<col;j++) {
				matrix[i][j]=sc.nextInt();
			}
		}
		try {
			System.out.println("Enter indices to access");
			int o=sc.nextInt();
			int p=sc.nextInt();
			int val=matrix[o][p];
			System.out.println("Value at indices "+o+" and "+p+" is "+val);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid indices, valid ones are between 0 and "+(rows-1)+"for rows and 0 and "+(col-1)+" for columns");
		}
		System.out.println("Enter number of rows for jagged matrix");
		int r=sc.nextInt();
		int[][] mat=new int[r][];
		for(int i=0;i<r;i++) {
			System.out.println("Enter number of elements for row "+i);
			int c=sc.nextInt();
			mat[i]=new int[c];
			System.out.println("Now enter elements");
			for(int j=0;j<c;j++) {
				int x=sc.nextInt();
				mat[i][j]=x;
			}
		}
		try {
			System.out.println("Enter indices to access");
			int a=sc.nextInt();
			int b=sc.nextInt();
			System.out.println("Value at indices "+a+" and "+b+" is "+mat[a][b]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out .println("Invalid indices");
		}

	}

}
