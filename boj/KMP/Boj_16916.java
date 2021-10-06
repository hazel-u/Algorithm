package boj.KMP;

import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;

public class Boj_16916 {

	static int ans=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String P = br.readLine();
		
		KMP(S,P);
		System.out.println(ans);
	}
	
	static int[] getPi(String P) {
		int[] pi = new int[P.length()];
		
		for(int i=1, j=0; i<P.length();i++) {
			while(j>0 && P.charAt(i)!=P.charAt(j)) j = pi[j-1];
			if(P.charAt(i)==P.charAt(j)) pi[i] = ++j;
		}
		
		return pi;
	}
	
	static void KMP(String S, String P) {
		int[] pi = getPi(P);
		
		for(int i=0, j=0; i<S.length(); i++) {
			while(j>0 && S.charAt(i)!=P.charAt(j)) j=pi[j-1];
			if(S.charAt(i)==P.charAt(j)) {
				if(j==P.length()-1) {
					ans=1;
					break;
				}
				else j++;
			}
		}
	}
}
