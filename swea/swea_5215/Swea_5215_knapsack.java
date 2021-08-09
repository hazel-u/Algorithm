package swea.swea_5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_5215_knapsack {
	
	static int maxScore;
	static int[] score;
	static int[] cal;
	static int N;
	static int L;
	static int[][] dp;
	
	
	static int knapsack(int idx, int remain) {
		if(idx==N) return 0;
		int temp = dp[idx][remain];
		if(temp!=-1) return temp;
		if(cal[idx]<=remain) {
			temp = knapsack(idx+1, remain-cal[idx])+score[idx];
		}
		temp = Math.max(temp, knapsack(idx+1,remain));
		maxScore = Math.max(maxScore, temp);
		return dp[idx][remain]=temp;
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료의 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			maxScore=0;
			
			score=new int[N];
			cal = new int[N];
			
			dp = new int[N][L+1];
			
			// 민기의 점수 T, 칼로리 K
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				score[n]=Integer.parseInt(st.nextToken());
				cal[n]=Integer.parseInt(st.nextToken());
				for(int i=0; i<=L; i++) {
					dp[n][i]=-1;
				}
			}
			
			knapsack(0,L);			
			
			System.out.println("#"+t+" "+maxScore);
		}
	}
}
