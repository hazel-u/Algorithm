package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14002 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] A = new int[N];
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		
		int in=0;
		while(st.hasMoreTokens()) {
			A[in++]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(A[i]>A[j] && dp[i]<dp[j]+1) {
					dp[i]=dp[j]+1;
				}
			}
		}
		
		int maxDPV=0; // 가장 큰 dp
		int maxDPI=-1; // 가장 큰 dp값이 있는 곳의 index
		int maxAV=A[maxDPI];
		
		for(int i=0; i<N; i++) {
			if(dp[i]>maxDPV) {
				maxDPV=dp[i];
				maxDPI=i;
			}
		}
		System.out.println(maxDPV);
		
		for(int i=N; i>0; i--) {
			if()
		}
	}
}
