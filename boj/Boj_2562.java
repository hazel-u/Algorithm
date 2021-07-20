package boj;

import java.util.Scanner;

public class Boj_2562 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = 9;
		int index=9;
		int max = -1;
		int num=-1;
		
		for(int i=0; i<N; i++) {
			num = scan.nextInt();
			if(num>max) {
				max = num;
				index = i+1;
			}
		}
		
		System.out.println(max);
		System.out.println(index);
	}

}
