package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_1463 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		Arrays.fill(dp, 987654321);
		dp[1]=0;
		if(N>1) {
			dp[2]=1;
			if(N>2) dp[3]=1;
		}
		
		for(int i=4; i<=N; i++) {
			if(i%3==0) {
				dp[i] = Math.min(dp[i], dp[i/3]+1);
			}
			if(i%2==0) {
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			}
			dp[i] = Math.min(dp[i], dp[i-1]+1);
		}
		
		System.out.println(dp[N]);
	}

}
