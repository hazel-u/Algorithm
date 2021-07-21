package swea.basic;

import java.util.Scanner;

public class Swea_2070 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		for(int i=0;i<N;i++) {
			int num1 = scan.nextInt();
			int num2 = scan.nextInt();
			
			if(num1<num2) {
				System.out.printf("#%d <\n",i+1);
			}else if(num1>num2) {
				System.out.printf("#%d >\n",i+1);
			}else {
				System.out.printf("#%d =\n",i+1);
			}
		}
	}

}
