package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_15993 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		/*
		 	 주의할 것
		 	 1. testCase 방식이기 때문에 testCase마다 dp를 구하는 방법은 시간 초과를 유발한다.
		 	 2. 마지막에 모듈러로 10억 9를 나누기 때문에 dp를 int로 하게 되면 값을 못구하게 된다. => long 사용하기
		*/
		
		long[][] dp = new long[100001][2]; // 짝수, 홀수
		dp[1][0]=0;
		dp[1][1]=1;
		dp[2][0]=1;
		dp[2][1]=1;
		dp[3][0]=2;
		dp[3][1]=2;
		
		for(int i=4; i<100001; i++) {
			dp[i][0]+=(dp[i-3][1]+dp[i-2][1]+dp[i-1][1])%1000000009;
			dp[i][1]+=(dp[i-3][0]+dp[i-2][0]+dp[i-1][0])%1000000009;
		}
		
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			bw.write(dp[n][1]+" "+dp[n][0]+"\n");
		}
		bw.flush();
	}
}
