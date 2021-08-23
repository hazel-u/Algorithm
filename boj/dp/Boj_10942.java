package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_10942 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = make(N, arr);
		
		int M = Integer.parseInt(br.readLine());		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			bw.write(dp[s][e]+"\n");
		}
		bw.flush();
	}
	
	private static int[][] make(int N, int[] arr) {
		int[][] dp = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			dp[i][i]=1;
		}
		
		for(int i=1; i<N; i++) {
			if(arr[i]==arr[i+1]) {
				dp[i][i+1]=1;
			}
		}
		
		for(int i=2; i<N; i++) {
			for(int j=1; j<=N-i; j++) {
				if(arr[j]==arr[j+i] && dp[j+1][j+i-1]==1) {
					dp[j][i+j]=1;
				}
			}
		}
		
		return dp;
	}
}
