package swea.basic;

import java.util.Scanner;

public class Swea_2071 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		for(int i=0; i<N; i++) {
			int sum=0;
			for(int j=0; j<10; j++) {
				sum+=scan.nextInt();
			}
			System.out.printf("#%d %.0f\n", i+1, (float)sum/10.0f);
		}
	}

}
