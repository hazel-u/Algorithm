package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_8320 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int cnt=0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i*j<=N && i<=j) {
						cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
