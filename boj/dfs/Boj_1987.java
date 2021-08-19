package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1987 {
	
	static int[][] map;
	static boolean[] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int R,C;
	static int max = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		visited = new boolean[26]; // 0~9
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j]= s.charAt(j)-65;
			}
		}
		
		dfs(0,0,1);
		System.out.println(max);
	}
	
	private static void dfs(int r, int c, int cnt) {
		visited[map[r][c]]=true;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr>=0 && nr<R && nc>=0 && nc<C) { // 범위 안에 있고
				if(!visited[map[nr][nc]]) { // 지나간 적이 없는 알파벳이면
					dfs(nr,nc,cnt+1);
				}
			}
		}
		visited[map[r][c]]=false;
		max = Math.max(max, cnt);
	}
}
