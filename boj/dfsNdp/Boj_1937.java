package boj.dfsNdp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1937 {
	static int n;
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map=new int[n][n];
		dp=new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int maxV=0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				maxV=Math.max(maxV, dfs(i,j));
			}
		}
		System.out.println(maxV);
	}
	
	static int dfs(int r, int c) {
		if(dp[r][c]!=0) {
			return dp[r][c];
		}
		
		dp[r][c]=1;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			// 범위확인
			if(nr>-1 && nr<n && nc>-1 && nc<n) {
				if(map[nr][nc]>map[r][c]) {
					// 상하좌우 중에서 가장 오래 생존할 수 있는 것
					dp[r][c] = Math.max(dp[r][c], dfs(nr,nc)+1); // 지금 이 자리가 오래 생존한건지, 아니면 다른 곳을 들려 넘어오는게 오래 생존한건지 비교
				}
			}
		}
		
		return dp[r][c];
	}
}
