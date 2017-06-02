import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);
		KNN knn=new KNN("input.txt");
		System.out.print("Input The Tuple :");
		final String tuple=scanner.nextLine();
		knn.setInputTuple(tuple);
		System.out.print("Input Value Of K :");
		final int k=scanner.nextInt();
		knn.setK(k);
		knn.performKNN();

	}

}
