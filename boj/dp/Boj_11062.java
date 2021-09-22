package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11062 {
	
	static int[][][] dp;
	static int[] cards;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			cards = new int[N];
			
			for(int n=0; n<N; n++) {
				cards[n]=Integer.parseInt(st.nextToken());
			}
			
			// 게임 시작
			dp = new int[2][N][N];
			for(int i=0; i<2; i++) {
				for(int j=0; j<N; j++) Arrays.fill(dp[i][j], -1);
			}
			
			// 0 근우 ,  1 명우
			bw.write(solve(0, 0, N-1)+"\n");
		}
		bw.flush();
	}
	
	public static int solve(int who, int start, int end) {
		
		if(dp[who][start][end]!=-1) return dp[who][start][end]; // 처음 방문하는 dp가 아니라면 이미 있던 값 넣기
		
		if(start>=end) {
			if(who==0) { // 근우 차례라면 
				return cards[start];
			}
			else return 0;
		}
		
		
		dp[who][start][end]=0;
		
		if(who==0) {
			dp[who][start][end]=Math.max(solve(who+1,start+1,end)+cards[start], solve(who+1,start,end-1)+cards[end]);
		}
		else {
			dp[who][start][end]=Math.min(solve(who-1, start+1, end), solve(who-1,start,end-1));
		}
		
		return dp[who][start][end];
	}
}
