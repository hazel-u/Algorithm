package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1600 {
	
	static int K, W, H;
	static int[][] map;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// 상왼, 상우, 왼상, 왼하, 하왼, 하우, 오상, 오하
	static int[] hr = {-2, -2, -1, 1, 2, 2, -1, 1};
	static int[] hc = {-1, 1, -2, -2, -1, 1, 2, 2};
	
	static class Pos{
		int r; // 현재 위치 r
		int c; // 현재 위치 c
		int cnt; // 몇번 움직였는지
		int k; // 남은 말 움직임 횟수가 얼마인지
		
		public Pos(int r, int c, int cnt, int k) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.k = k;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(BFS(0,0));
		
	}
	

	static int BFS(int start_r, int start_c) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(start_r, start_c, 0, K));
		boolean[][][] visited = new boolean[H][W][K+1];
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int cr = cur.r;
			int cc = cur.c;
			int ccnt = cur.cnt;
			int ck = cur.k;
			
			// 같은 방법으로 이 곳에 온적있나 체크
			if(visited[cr][cc][ck]) continue;
			visited[cr][cc][ck]=true;
			
			if(cr==H-1 && cc==W-1) { // 목적지에 도착했을 경우
				return ccnt;
			}
			
			// 인접한 상하좌우로 움직이는 경우
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				if(nr>=0 && nr<H && nc>=0 && nc<W) { // 유효한 범위 내에 있고
					if(map[nr][nc]!=1) { // 갈 위치가 장애물이 아니면
						q.add(new Pos(nr, nc, ccnt+1, ck));
					}
				}
			}
			
			// 말의 움직임을 따라하는 경우
			if(ck>0) {
				for(int h=0; h<8; h++) {
					int nr = cr+hr[h];
					int nc = cc+hc[h];
					
					if(nr>=0 && nr<H && nc>=0 && nc<W) {
						if(map[nr][nc]!=1) {
							q.add(new Pos(nr, nc, ccnt+1, ck-1));
						}
					}
				}
			}
		}
		
		return -1;
	}
}











