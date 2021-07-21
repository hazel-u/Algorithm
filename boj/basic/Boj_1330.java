package boj.basic;

import java.util.Scanner;

public class Boj_1330 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		
		if(num1>num2) {
			System.out.println(">");
		}else if(num1<num2) {
			System.out.println("<");
		}else {
			System.out.println("==");
		}
		
	}
}
