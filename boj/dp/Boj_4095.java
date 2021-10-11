package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_4095 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int maxSize = 0;
			
			if(N==0 && M==0) break;
			
			int[][] map = new int[N][M];
			int[][] dp = new int[N][M];
			
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				for(int m=0; m<M; m++) {
					map[n][m]=Integer.parseInt(st.nextToken());
					if(n==N-1 || m==M-1) dp[n][m]=map[n][m];
					if(map[n][m]==1) maxSize=1;
				}
			}
			
			for(int r=N-2; r>=0; r--) {
				for(int c=M-2; c>=0; c--) {
					if(map[r][c]!=0) {
						dp[r][c] = Math.min(dp[r][c+1], Math.min(dp[r+1][c], dp[r+1][c+1]))+1;
						maxSize = Math.max(maxSize, dp[r][c]);
					}
				}
			}
			bw.write(maxSize+"\n");
		}
		bw.flush();
	}
}
