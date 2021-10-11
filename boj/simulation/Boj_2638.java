package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2638 {

	static int N,M;
	static int[][] map;
	static int cheeseCnt=0;
	
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
		
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0; m<M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if(map[n][m]==1) cheeseCnt++;
			}
		}
		// ---------- 입력 끝 -----------
		
		System.out.println(simulation());
	}

	public static int simulation() {
		int time=0;
		
		while(true) {
			if(cheeseCnt==0) return time;
			
			// 외부공기 확인
			int[][] air = findAir(); // 외부공기면 1, 아니면 0
			// 치즈 확인
			int[][] cheese = findAliveCheese(air);
			// map배열에 cheese copy
			map=cheese;
			
			// 시간 증가
			time++;
		}
	}
	
	private static int[][] findAliveCheese(int[][] air) {
		int[][] cheese = new int[N][M];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c]==1) { // 치즈이면
					int airCnt=0;
					for(int d=0; d<4; d++) {
						if(air[r+dr[d]][c+dc[d]]==1) {
							airCnt++;
						}
					}
					if(airCnt<2) { // 외부공기와 맞닿는 부분이 1변 이하이면
						cheese[r][c]=1;
					}
					else { // 외부 공기와 맞닿는 부분이 2변 이상이면
						cheeseCnt--; // cheese개수 감소
					}
				}
			}
		}
		
		return cheese;
	}

	public static int[][] findAir(){
		int[][] air = new int[N][M];
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(new Pos(0,0));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M) {
					if(air[nr][nc]==0 && map[nr][nc]==0) {
						air[nr][nc]=1;
						q.add(new Pos(nr,nc));
					}
				}
			}
		}
		
		return air;
	}
}
