package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2056_dp {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		int ans = 0;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			dp[i]=time;
			
			for(int c=0; c<count; c++) {
				int prev = Integer.parseInt(st.nextToken());
				
				dp[i]=Math.max(dp[i],dp[prev]+time);
			}
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
	}

}
