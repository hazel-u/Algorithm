package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_16172 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine().replaceAll("[0-9]", "");
		String k = br.readLine();
		
		
		System.out.println(KMP(s,k));
	}
	
	static int KMP(String s, String p) {
		int[] pi=getPi(p);
		
		for(int i=0, j=0; i<s.length(); i++) {
			while(j>0 && s.charAt(i)!=p.charAt(j)) j=pi[j-1];
			if(s.charAt(i)==p.charAt(j)) {
				if(j==p.length()-1) {
					return 1;
				}
				else j++;
			}
		}
		
		return 0;
	}
	
	static int[] getPi(String p) {
		int[] pi = new int[p.length()];
		
		for(int i=1, j=0; i<p.length();i++) {
			while(j>0 && p.charAt(i)!=p.charAt(j)) j=pi[j-1];
			if(p.charAt(i)==p.charAt(j)) pi[i]=++j;
		}
		
		return pi;
	}
}
