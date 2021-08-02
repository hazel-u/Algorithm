package boj.dfsNdp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1520 {
	static int M;
	static int N;
	static int[][] map;
	static int[][] dp;
	// 상, 하, 좌, 우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				dp[i][j]=-1;
			}
		}
		
		System.out.println(dfs(0,0));
		
	}
	
	public static int dfs(int r, int c) {
		
		dp[r][c]=0; // 방문했다는 뜻
		
		for(int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if((nr>=0 && nr<M) && (nc>=0 && nc<N)) { // 범위안에 있으면
				if(map[nr][nc] < map[r][c]) {
					if(nr==M-1 && nc==N-1) {
						dp[r][c]+=1;
					}
					
					if(dp[nr][nc]>=0) { // 이미 데이터가 있는 곳이면
						dp[r][c] += dp[nr][nc];
					}else {
						dp[r][c]+=dfs(nr,nc);
					}
				}
			}
		}
		
		return dp[r][c];
		
	}

}
