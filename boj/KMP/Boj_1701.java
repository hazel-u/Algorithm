package boj.KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1701 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		int len = str.length();
		int max = 0;
		
		for(int i=0; i<len; i++) {
			max = Math.max(max, KMP(str.substring(i, len)));
		}
		
		System.out.println(max);
	}

	public static int KMP(String str) {
		int max=0;
		int len = str.length();
		int pi[] = new int[len];
		
		for(int i=1, j=0; i<len; i++) {
			while(j>0 && str.charAt(i)!=str.charAt(j)) j = pi[j-1];
			if(str.charAt(i)==str.charAt(j)) max = Math.max(max, pi[i]=++j);
		}
		
		return max;
	}
}
