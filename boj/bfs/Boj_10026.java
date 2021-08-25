package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj_10026 {
	static int N;
	static char[][] original_map;
	static char[][] RG_map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int original_cnt;
	static int RG_cnt;
	static boolean[][] original_visited;
	static boolean[][] RG_visited;
	
	static class Pos{
		int r,c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		original_map = new char[N][N];
		RG_map = new char[N][N];
		original_visited = new boolean[N][N];
		RG_visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				char c = s.charAt(j);
				
				original_map[i][j]=c;
				if(c=='G') {
					RG_map[i][j]='R';
				}else RG_map[i][j]=c;
			}
		}
		
		original_bfs(0,0);
		RG_bfs(0,0);
		System.out.println(original_cnt+" "+RG_cnt);
		
	}
	
	private static boolean isIn(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}
		return false;
	}
	
	private static void original_bfs(int sr, int sc) {
		original_cnt++;
		
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(sr, sc));
		original_visited[sr][sc]=true;
		char color = original_map[sr][sc];
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(isIn(nr, nc) && !original_visited[nr][nc]) {
					if(original_map[nr][nc]==color) {
						q.offer(new Pos(nr,nc));
						original_visited[nr][nc]=true;
					}
				}
			}
		}
		if(sr==0 && sc==0) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!original_visited[i][j]) {
						original_bfs(i,j);
					}
				}
			}
		}
	}

	private static void RG_bfs(int sr, int sc) {
		RG_cnt++;
		
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(sr, sc));
		RG_visited[sr][sc]=true;
		char color = RG_map[sr][sc];
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(isIn(nr, nc) && !RG_visited[nr][nc]) {
					if(RG_map[nr][nc]==color) {
						q.offer(new Pos(nr,nc));
						RG_visited[nr][nc]=true;
					}
				}
			}
		}
		if(sr==0 && sc==0) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!RG_visited[i][j]) {
						RG_bfs(i,j);
					}
				}
			}
		}
	}
	
}
