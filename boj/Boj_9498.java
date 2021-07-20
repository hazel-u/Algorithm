package boj;

import java.util.Scanner;

public class Boj_9498 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int score = scan.nextInt()/10;
		
		char grade='F';
		
		switch(score) {
		case 10:
			grade='A';
			break;
		case 9:
			grade='A';
			break;
		case 8:
			grade='B';
			break;
		case 7:
			grade='C';
			break;
		case 6:
			grade='D';
			break;
		default:
			break;
			
		}
		System.out.println(grade);
	}
}
