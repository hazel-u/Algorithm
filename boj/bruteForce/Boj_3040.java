package boj.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_3040 {
	static int[] p;
	static int n;
	static int r;
	static int[] comb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=9;
		r=7;
		p=new int[n];
		comb=new int[r];
		
		for(int i=0; i<n; i++) {
			p[i]=Integer.parseInt(br.readLine());
		}
		combination(0,0);		
	}
	
	public static void combination(int cnt, int start) {
		if(cnt==r) {
			int sum=0;
			for(int i=0; i<7; i++) {
				sum+=comb[i];
			}
			
			if(sum==100) {
				for(int i=0; i<7; i++) {
					System.out.println(comb[i]);
				}
			}
			return;
		}
		
		for(int i=start; i<n; i++) {
			comb[cnt]=p[i];
			
			combination(cnt+1, i+1);
		}
	}
}
