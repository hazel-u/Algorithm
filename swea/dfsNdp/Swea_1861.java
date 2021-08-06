package swea.dfsNdp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_1861 {
	
	static int[][] map;
	static int[][] dp;
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dp = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					dp[i][j]=0;
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(dp[i][j]==0) {
						dp[i][j]=dfs(i,j);
					}
				}
			}
			
			int maxV=-1;
			int maxStart=987654321;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(dp[i][j]>maxV) {
						maxV=dp[i][j];
						maxStart = map[i][j];
					}else if(dp[i][j]==maxV) {
						maxStart = Math.min(map[i][j], maxStart);
					}
				}
			}
			bw.write("#"+t+" "+maxStart+" "+maxV+"\n");
		}
		bw.flush();
	}
	
	public static int dfs(int r, int c) {
		if(dp[r][c]!=0) return dp[r][c];
		
		dp[r][c]=1;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr>-1 && nr<N && nc>-1 && nc<N) {
				if(map[r][c]+1 == map[nr][nc]) {
					if(dp[nr][nc]!=0) dp[r][c]+=dp[nr][nc];
					else dp[r][c]+=dfs(nr,nc);
				}
			}
		}
		return dp[r][c];
	}
}
