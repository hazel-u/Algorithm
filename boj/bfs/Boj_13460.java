package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_13460 {
	
	static int N,M;

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static char[][] map;
//	static boolean[][] Rvisited;
//	static boolean[][] Bvisited;
	
	static Queue<ball> rq;
	static Queue<ball> bq;
	
	static int goal_r, goal_c;
	
	static class ball{
		int r;
		int c;
		int cnt;
		
		public ball(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N+2][M+2];
		
		
		rq = new LinkedList<>();
		bq = new LinkedList<>();
		
		for(int i=0; i<N+2; i++) {
			Arrays.fill(map[i], '#');
		}
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i+1][j+1]=str.charAt(j);
				
				if(map[i+1][j+1]=='R') rq.add(new ball(i+1,j+1,0));
				else if(map[i+1][j+1]=='B') bq.add(new ball(i+1,j+1,0));
				else if(map[i+1][j+1]=='O') {
					goal_r=i+1;
					goal_c=j+1;
				}
			}
		}
		
		System.out.println(bfs());
		
	}

	static int bfs() {

		while(!rq.isEmpty()) {
			ball red = rq.poll();
			ball blue = bq.poll();
			
			int cnt = red.cnt;
			if(cnt==10) return -1;
			
			
			if(red.r==blue.r && red.c==blue.c) continue;
			if(blue.r==goal_r && blue.c==goal_c) continue; // 빨간 공보다 파란공이 먼저 골에 들어가거나 동시에 들어가면 실패
			else if(red.r==goal_r && red.c==goal_c) return cnt;
			
			for(int d=0; d<4; d++) {
				// 가는 도중 구멍에 빠졌나 확인
				boolean Rgoal=false;
				boolean Bgoal=false;
				
				// 구른 횟수
				int Rcnt=0;
				int Bcnt=0;
				
				// 벽에 닿을 때까지 굴러
				int Rnr=red.r;
				int Rnc=red.c;
				while(map[Rnr+dr[d]][Rnc+dc[d]]!='#') {
					Rnr+=dr[d];
					Rnc+=dc[d];
					if(map[Rnr][Rnc]=='O') Rgoal=true;
				}
				
				int Bnr=blue.r;
				int Bnc=blue.c;
				while(map[Bnr+dr[d]][Bnc+dc[d]]!='#') {
					Bnr+=dr[d];
					Bnc+=dc[d];
					if(map[Bnr][Bnc]=='O') Bgoal=true;
				}
				
				if(Bgoal) continue;
				else if(Rgoal) return cnt+1;
				
				if(Bnr==Rnr && Bnc==Rnc) {
					if(Rcnt>Bcnt){
						Rnr-=dr[d];
						Rnc-=dc[d];
					}else {
						Bnr-=dr[d];
						Bnc-=dc[d];
					}
				}
				
				// 빨간공
				rq.add(new ball(Rnr, Rnc, cnt+1));
				
				// 파란공
				bq.add(new ball(Bnr, Bnc, cnt+1));
			}
			
		}
		
		return -1;
	}
}
