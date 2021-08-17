package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2839 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int w5=N/5;
		int w3=0;
		int r = N%5;
		if(r==0) System.out.println(w5);
		else {
			while(r<=N) {
				if(r%3==0) {
					w3 = r/3;
					break;
				}else {
					w5--;
					r=r+5;
				}
			}
			if(w3>0) System.out.println(w5+w3);
			else System.out.println(-1);
		}
	}

}
