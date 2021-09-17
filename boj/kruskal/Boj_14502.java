package boj.kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_14502 {
	
	static int N,M;
	static int[][] map;
	static int[][] tempMap;
	static ArrayList<Pos> empty;
	static ArrayList<Pos> virus;
	static Pos[] res;
	static int max;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};

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
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		empty = new ArrayList<>();
		virus = new ArrayList<>();
		
		res = new Pos[3];
		
		max = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				
				if(map[i][j]==0) {
					empty.add(new Pos(i,j));
				}
				else if(map[i][j]==2) {
					virus.add(new Pos(i,j));
				}
			}
		}
		
		combination(0,0);
		
		System.out.println(max);
		
	}

	static void combination(int start, int cnt) {
		if(cnt==3) {
			tempMap = new int[N][M];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					tempMap[i][j] = map[i][j];
				}
			}
			
			// 3개 벽 세우기
			for(int i=0; i<3; i++) {
				int r = res[i].r;
				int c = res[i].c;
				tempMap[r][c]=1;
			}
			
			for(int i=0; i<virus.size(); i++) {
				bfs(virus.get(i).r, virus.get(i).c);
			}
			
			max = Math.max(max, safeZone());
			
			return;
		}
		
		for(int i=start; i<empty.size(); i++) {
			res[cnt] = empty.get(i);
			combination(i+1, cnt+1);
		}
	}
	
	static void bfs(int sr, int sc) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(sr, sc));

		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int cr = cur.r;
			int cc = cur.c;
			
			for(int d=0; d<4; d++) {
				int nr = cr+dr[d];
				int nc = cc+dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M && tempMap[nr][nc]==0) {
					tempMap[nr][nc]=2;
					q.add(new Pos(nr, nc));
				}
			}
		}
	}
	
	static int safeZone() {
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tempMap[i][j]==0) cnt++;
			}
		}
		
		return cnt;
	}
}