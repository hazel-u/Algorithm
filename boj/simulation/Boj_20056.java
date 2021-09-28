package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_20056 {

	static int N,M,K;
	static ArrayList<Fire>[][] map;
	
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	
	static class Fire{
		int m; // 질량
		int s; // 방향
		int d; // 속력
		public Fire(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]=new ArrayList<>();
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r =Integer.parseInt(st.nextToken());
			int c =Integer.parseInt(st.nextToken());
			int m =Integer.parseInt(st.nextToken());
			int s =Integer.parseInt(st.nextToken());
			int d =Integer.parseInt(st.nextToken());
			
			map[r-1][c-1].add(new Fire(m,s,d));
		}
		
		for(int k=0; k<K; k++) {
			move();
			split();
		}
		
		// 남은 파이어볼의 질량 더하기
		int sum = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c].size()>0) {
					for(int i=0; i<map[r][c].size(); i++) {
						sum+=map[r][c].get(i).m;
					}
				}
			}
		}
		
		System.out.println(sum);
	}

	static void move() {
		ArrayList<Fire>[][] newMap = new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				newMap[i][j]=new ArrayList<>();
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c].size()>0) { // 파이어볼이 있는 칸일 경우
					for(int i=0; i<map[r][c].size(); i++) {
						Fire cur = map[r][c].get(i);
						
						int nr = r+(dr[cur.d]*(cur.s%N));
						int nc = c+(dc[cur.d]*(cur.s%N));
						if(nr<0) nr+=N;
						if(nc<0) nc+=N;
						if(nr>=N) nr-=N;
						if(nc>=N) nc-=N;
						
						newMap[nr][nc].add(new Fire(cur.m,cur.s,cur.d));
					}
				}
			}
		}
		map=newMap;
	}
	
	static void split() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c].size()>=2) { // 해당 칸에 있는 파이어볼이 2개 이상이라면
					int cnt = map[r][c].size();
					int all_m = 0;
					int all_s = 0;
					int even_odd = map[r][c].get(0).d%2;
					boolean sameD=true;
					for(int i=0; i<cnt; i++) {
						Fire cur = map[r][c].get(i);
						all_m += cur.m;
						all_s += cur.s;
						if(even_odd != cur.d%2) {
							sameD=false;
						}
					}
					all_m/=5;
					all_s/=cnt;
					
					map[r][c].clear();
					if(all_m>0) {
						if(sameD) { // 파이어볼이 모두 홀수이거나 짝수
							for(int d=0; d<8; d+=2) {
								map[r][c].add(new Fire(all_m,all_s,d));
							}
						}else {
							for(int d=1; d<8; d+=2) {
								map[r][c].add(new Fire(all_m,all_s,d));
							}
						}
					}
				}
			}
		}
	}
}
