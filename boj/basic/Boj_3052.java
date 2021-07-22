package boj.basic;

import java.util.HashSet;
import java.util.Scanner;

public class Boj_3052 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		HashSet<Integer> set = new HashSet<Integer>();
		
		for(int i=0;i<10;i++) {
			int num = scan.nextInt() % 42;
			set.add(num);
		}
		
		System.out.println(set.size());
	}

}
