package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2999 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String msg = br.readLine();
		
		int N = msg.length();
		
		int R=1;
		int C=N;
		for(int i=1; i<N; i++) {
			if(N<4) {
				break;
			}
			if(i>=2 && N%i==0) {
				int temp = N/i;
				
				if(temp>i) {
					continue;
				}
				else if(temp<=i && R<temp) {
					R=temp;
					C=i;
				}
			}
		}
		
		char[][] arr = new char[R][C];
		int cnt=0;
		
		for(int i=0; i<C; i++) {
			for(int j=0; j<R; j++) {
				arr[j][i]=msg.charAt(cnt++);
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(arr[i][j]);
			}
		}
		System.out.println(sb.toString());
	}
}
