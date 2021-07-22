package boj.basic;

import java.util.Scanner;

public class Boj_8958 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		for(int i=0; i<N; i++)
		{
			int sum=0;
			String q = scan.next();
			int[] score = new int[q.length()];

			if(q.charAt(0)=='O') {
				score[0]=1;
			}
			else {
				score[0]=0;
			}
			
			for(int k=1; k<q.length(); k++) {
				if(q.charAt(k)=='O') {
					score[k]=score[k-1]+1;
				}else {
					score[k]=0;
				}
			}
			
			for(int k=0; k<q.length(); k++) {
				sum+=score[k];
			}
			System.out.println(sum);
			
		}
	}

}
