package swea.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Swea_1289 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			int cnt =0;
			String s = br.readLine();
			
			int cur = 0;
			
			for(int k=0; k<s.length(); k++) {
				if(cur==s.charAt(k)-'0') {
					continue;
				}else {
					cnt++;
					if(cur==0) cur=1;
					else cur=0;
				}
			}
			System.out.println("#"+(i+1)+" "+cnt);
		}
	}
}
