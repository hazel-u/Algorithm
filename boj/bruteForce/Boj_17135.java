package boj.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj_17135 {
	static int N;
	static int M;
	static int D;
	static int R=3; // 궁수 숫자
	static int[][] original_map;
	static int[] archer;
	static int max=-1;
	
	static class Pos{
		int r;
		int c;
		int dist;
		public Pos(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist=dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격거리 제한
		
		archer = new int[R];
		original_map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				original_map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0,0);
		
		System.out.println(max);
	}
	
	public static void combination(int cnt, int start) {
		if(cnt==R) {
			// 궁수 위치 지정 완료
			int[][] map = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[i][j]=original_map[i][j];
				}
			}
			
			fight(map);
			return;
		}

		for(int i=start; i<M; i++) {
			archer[cnt]=i;
			combination(cnt+1, i+1);
		}
	}
	
	public static void fight(int[][] map) {
		// 모든 궁수가 동시에 공격해야해
		int cnt=0;
		while(true) {
			
			ArrayList<ArrayList<Pos>> enemy = new ArrayList<ArrayList<Pos>>();
			for(int i=0; i<R; i++) {
				enemy.add(new ArrayList<Pos>());
			}
			for(int i=0; i<R; i++) {
				// 각 궁수마다 적과의 거리 계산해서 죽이기 (궁수의 위치는 N,c[i])
				for(int r=N-1; r>=0; r--) {
					for(int c=0; c<M; c++) {
						if(map[r][c]==1) { // 적의 위치
							// 적과 궁수와의 거리
							int d = Math.abs(N-r)+Math.abs(archer[i]-c);
							if(d<=D) {
								enemy.get(i).add(new Pos(r,c,d));
							}
						}
					}
				}
				Collections.sort(enemy.get(i), new Comparator<Pos>(){ // 거리가 가장 짧은 곳에 있는 것, 같은 거리가 여러개라면 가장 왼쪽에 있는 것
					@Override
					public int compare(Pos o1, Pos o2) {
						if(o1.dist==o2.dist) {
							return o1.c-o2.c;
						}else return o1.dist-o2.dist;
					}
				});
			}
			for(int k=0; k<R; k++) {
				// 적 없애기
				if(enemy.get(k).size()>0) {
					if(map[enemy.get(k).get(0).r][enemy.get(k).get(0).c]==1) {
						map[enemy.get(k).get(0).r][enemy.get(k).get(0).c]=0;
						cnt++;
					}
				}
			}
			
			// 이걸 돌고 난 후에는 적의 위치 바꿔야돼 (적을 한칸 내려야해)
			// 적이 성이 있는 칸으로 들어오면 없어지고,
			// map전체에 적이 한명도 남아있지 않으면 게임 끝
			for(int i=N-1; i>=0; i--) {
				for(int j=M-1; j>=0; j--) {
					if(i==N-1 && map[i][j]==1) { // 적이 성에 들어왔을 때
						map[i][j]=0;
					}else if(map[i][j]==1) { // 적이 성에 아직 못들어왔을 때
						map[i][j]=0;
						map[i+1][j]=1;
					}
				}
			}
			
			// 게임이 끝났나 확인
			boolean flag=false;
			f2:for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==1) {
						flag=true;
						break f2;
					}
				}
			}
			
			if(!flag) {
				max = Math.max(max, cnt);
				return;
			}
		}
		
	}
}
