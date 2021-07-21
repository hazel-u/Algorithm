package swea.basic;

import java.util.Scanner;

public class Swea_2068 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		for(int i=0;i<N;i++) {
			int max = -1;
			for(int j=0; j<10; j++) {
				int num = scan.nextInt();
				if(num>max) {
					max=num;
				}
			}
			System.out.printf("#%d %d\n",i+1, max);
		}
	}

}
