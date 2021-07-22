package boj.basic;

import java.util.HashSet;
import java.util.Scanner;

public class Boj_2577 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] arr = new int[10];
		
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		int abc = a*b*c;
		String res = Integer.toString(abc);
		
		for(int i=0; i<res.length(); i++) {
			arr[res.charAt(i)-'0']+=1;
		}
		
		for(int i=0; i<10; i++) {
			System.out.println(arr[i]);
		}
	}

}
