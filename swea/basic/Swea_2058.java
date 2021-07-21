package swea.basic;

import java.util.Scanner;

public class Swea_2058 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String num = scan.next();
		int sum=0;
		
		for(int i=0; i<num.length(); i++) {
			sum += num.charAt(i)-'0';
		}
		
		System.out.println(sum);

	}

}
