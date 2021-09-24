package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7576 {

	static int R,C;
	static int rest;
	static int[][] map;
	static Queue<Tomato> q;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	
	static class Tomato{
		int r;
		int c;
		int day;
		
		public Tomato(int r, int c, int day) {
			super();
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) rest++;
				if(map[i][j]==1) q.add(new Tomato(i,j,0));
			}
		}
		
		if(rest==0) {
			System.out.println(0);
			return;
		}
		rest+=q.size();
		System.out.println(bfs());
		
	}
	
	public static int bfs() {
		
		while(!q.isEmpty()) {
			Tomato cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int day = cur.day;
			
			if(--rest==0) return day;
			
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
				else if(map[nr][nc]==0) {
					map[nr][nc]=1;
					q.add(new Tomato(nr,nc,day+1));
				}
			}
		}
		
		return -1;
	}
}
