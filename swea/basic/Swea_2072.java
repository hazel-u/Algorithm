package swea.basic;

import java.util.Scanner;

public class Swea_2072 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		int num=0;
		
		for(int i=0; i<N; i++) {
			int sum =0;
			for(int j=0; j<10; j++) {
				num = scan.nextInt();
				if(num%2!=0) {
					sum+=num;
				}
			}
			System.out.println("#"+(i+1)+" "+sum);
		}
	}

}
