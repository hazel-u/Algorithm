package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1194 {

	static int N,M;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static boolean[][][] visited;
	static char[][] map;
	
	static class Pos{
		int r;
		int c;
		int dist;
		int keys;
		public Pos(int r, int c, int dist, int keys) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.keys = keys;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		map = new char[N][M];
		visited = new boolean[N][M][64];
		
		int[] start = new int[2]; // r,c
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]=s.charAt(j);
				if(map[i][j]=='0') {
					start[0]=i;
					start[1]=j;
				}
			}
		}
		
		int res = find(start[0],start[1]);
		System.out.println(res);
	}
	
	public static int find(int sr, int sc) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(sr,sc,0,0));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int ck = cur.keys;

			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc][ck]) {
					if(map[nr][nc]!='#') {
						if(map[nr][nc]=='1') { // 도착지이면
							return cur.dist+1;
						}if(map[nr][nc]=='.' || map[nr][nc]=='0') { // 빈 곳이면
							visited[nr][nc][ck]=true;
							q.offer(new Pos(nr,nc,cur.dist+1,ck));
						}else if(map[nr][nc]-'a'>=0 && map[nr][nc]-'a'<6) { // 소문자이면
							int tempKeys = (1 << (map[nr][nc]-'a')) | ck;
							visited[nr][nc][tempKeys]=true;
							q.offer(new Pos(nr,nc,cur.dist+1,tempKeys));
						}else if(map[nr][nc]-'A'>=0 && map[nr][nc]-'A'<6) { // 대문자이면
							if(((1<<map[nr][nc]-'A') & ck)>0) {
								visited[nr][nc][ck]=true;
								q.offer(new Pos(nr,nc,cur.dist+1,ck));
							}
						}
					}
				}
			}
		}
		return -1;
	}
}
