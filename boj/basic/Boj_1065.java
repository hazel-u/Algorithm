package boj.basic;

import java.util.Scanner;

public class Boj_1065 {
	
	static boolean c(int num) {
		boolean res = true;
		
		if(num<100) {
			return res;
		}
		else {
			String s_num = Integer.toString(num);
			int sub=s_num.charAt(0) - s_num.charAt(1);
			for(int i=1; i<s_num.length()-1; i++) {
				if(sub != s_num.charAt(i)-s_num.charAt(i+1)) {
					return false;
				}
			}
		}
		
		
		return res;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		int answer=0;
		
		for(int i=1; i<N+1; i++) {
			boolean check = c(i);
			if(check) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
