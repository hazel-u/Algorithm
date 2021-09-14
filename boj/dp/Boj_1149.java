package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][3]; // 빨, 초, 파
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				info[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N][3];
		dp[0][0]=info[0][0];
		dp[0][1]=info[0][1];
		dp[0][2]=info[0][2];
		
		for(int i=1; i<N; i++) {
			dp[i][0] = info[i][0]+Math.min(dp[i-1][1],dp[i-1][2]);
			dp[i][1] = info[i][1]+Math.min(dp[i-1][0],dp[i-1][2]);
			dp[i][2] = info[i][2]+Math.min(dp[i-1][0],dp[i-1][1]);
		}
		
		int res = 987654321;
		for(int i=0; i<3; i++) {
			res = Math.min(res, dp[N-1][i]);
		}
		
		System.out.println(res);
	}
}
