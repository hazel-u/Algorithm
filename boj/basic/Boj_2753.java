package boj.basic;

import java.util.Scanner;

public class Boj_2753 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int y = scan.nextInt();
		
		if(((y%4==0)&&(y%100!=0))||(y%400==0)) {
			System.out.println(1);
			return;
		}
		System.out.println(0);
	}
	
}
