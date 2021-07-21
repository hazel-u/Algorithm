package boj.basic;

import java.util.Scanner;

public class Boj_1100 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cnt = 0;
		
		for(int i=0; i<8;i++) {
			String temp = scan.next();
			for (int j=0; j<8; j++) {
				char a=temp.charAt(j);
				if(a=='F' && (i+j)%2==0) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

}
