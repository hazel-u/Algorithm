package boj.basic;

import java.util.Arrays;
import java.util.Scanner;

public class Boj_4344 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int C = scan.nextInt();
		
		for(int i=0; i<C; i++) {
			int N = scan.nextInt();
			int[] scores = new int[N];
			
			for(int k=0; k<N; k++) {
				scores[k]=scan.nextInt();
			}
			
			Arrays.sort(scores);
			int sum=0;
			for(int j=0; j<N;j++) {
				sum+=scores[j];
			}
			float avg = sum/(float)N;
			
			int cnt=0;
			
			
			for(int j=0; j<N; j++) {
				if(scores[j] > avg) {
					cnt++;
				}
			}
			
			float answer = (cnt/(float)N)*100;
			
			System.out.printf("%.3f%%\n",answer);
		}
	}
}
