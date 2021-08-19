package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_3109 {
	static char[][] map;
	static boolean[][] visited;
	static int R, C, find;
	static int[] dr = {-1,0,1};
	static int[] dc = {1,1,1};
	static boolean finish;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j]=s.charAt(j);
			}
		}
		
		for(int r=0; r<R; r++) {
			finish=false;
			findBread(r,0);
		}
		
		System.out.println(find);
	}
	
	
	private static void findBread(int r, int c) {
		if(finish) return;
		
		if(c==C-1) {
			find++;
			finish=true;
			return;
		}
		
		for(int d=0; d<3; d++) {
			if(finish) return;
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr<R && nc>=0 && nc<C) { // 범위안에 있고
				if(map[nr][nc]=='.' && !visited[nr][nc]) { // 건물이 아니고, 방문했던 곳이 아니면
					visited[nr][nc]=true;
					findBread(nr, nc);
				}
			}
		}
	}
}
