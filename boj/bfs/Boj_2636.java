package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2636 {
	
	// 상하좌우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int R,C;
	static int cheese;
	static int time;
	static int before;

	static int[][] map;
	
	static class Pos{
		int r;
		int c;
		
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		cheese = 0; // 처음 치즈 갯수
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) cheese++;
			}
		}
		
		// 시작점 찾기
		int r=0; 
		int c=0;
		f:for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]==0) {
					r=i;
					c=j;
					break f;
				}
			}
		}
		
		BFS(r,c);
		
		System.out.println(time);
		System.out.println(before);
	}

	static void BFS(int start_r, int start_c) {
		
		Queue<Pos> q = new LinkedList<>();
		
		while(true) {
			q.add(new Pos(start_r, start_c));
			boolean[][] visited = new boolean[R][C];
			
			time++;
			ArrayList<Pos> remove = new ArrayList<>();
			while(!q.isEmpty()) {
				Pos cur = q.poll();
				int cr = cur.r;
				int cc = cur.c;
				for(int d=0; d<4; d++) {
					int nr = cr+dr[d];
					int nc = cc+dc[d];
					if(nr>=0 && nr<R && nc>=0 && nc<C) {
						if(map[nr][nc]==0 && !visited[nr][nc]) {
							q.add(new Pos(nr,nc));
							visited[nr][nc]=true;
						}
						else if(map[nr][nc]==1 && !visited[nr][nc]) {
							remove.add(new Pos(nr, nc));
							visited[nr][nc]=true;
						}
					}
				}
			}
			
			before=cheese;
			for(int i=0; i<remove.size(); i++) {
				int rr = remove.get(i).r;
				int rc = remove.get(i).c;
				
				map[rr][rc]=0;
				cheese--;
			}
			if(cheese==0) return;
		}
	}
}







