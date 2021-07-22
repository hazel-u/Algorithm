package boj.basic;

import java.util.Arrays;
import java.util.Scanner;

public class Boj_1546 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		int[] score = new int[N];
		
		for(int i=0; i<N; i++) {
			score[i]=scan.nextInt();
		}
		Arrays.sort(score);
		int M = score[N-1];

		float sum = 0;
		for(int i=0; i<N; i++) {
			sum+=(float)score[i]/M*100;
		}
		
		float answer = sum / N;
		System.out.println(answer);
	}

}
