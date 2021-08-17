package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_2839 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		Arrays.fill(dp, -1);
		
		for(int i=0; i<=N; i++) {
			if(i==3 || i==5) {
				dp[i]=1;
			}else if(i>5){
				if(dp[i-3]>0) dp[i]=dp[i-3]+1;
				if(dp[i-5]>0 && dp[i]>0) dp[i]=Math.min(dp[i], dp[i-5]+1);
				else if(dp[i-5]>0) dp[i]=dp[i-5]+1;
			}
		}
		
		System.out.println(dp[N]);
	}

}
