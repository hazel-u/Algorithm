package bj;

import java.util.Scanner;

public class Bj_2588 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		
		// 3
		System.out.println(num1 * (num2%10));
		
		// 4
		System.out.println(num1 * ((num2/10)%10));
		
		// 5 
		System.out.println(num1 * (num2/100));
		
		// 6
		System.out.println(num1 * num2);
	}

}
