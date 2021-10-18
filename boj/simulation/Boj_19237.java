package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_19237 {

	static int N,M,K;
	
	// 위, 아래, 왼쪽, 오른쪽 = 1,2,3,4
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int[][] map;
	static Smell[][] smellMap;
	static int[] sharkDir;
	static int[][][] sharkPriority;
	
	static Queue<Shark> q;
	
	static class Shark{
		int num;
		int r;
		int c;
		public Shark(int num, int r, int c) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
		}
	}
	
	static class Smell{
		int time;
		int num;
		public Smell(int time, int num) {
			super();
			this.time = time;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N]; // 상어 위치
		smellMap = new Smell[N][N]; // 냄새
		sharkDir = new int[M]; // 방향
		sharkPriority = new int[M][4][4]; // 각 상어의 방향 우선순위(한 상어가, 해당 방향을 보고있을 때, 우선순위)
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken())-1; // 빈 곳은 -1
				if(map[i][j]>=0) q.offer(new Shark(map[i][j],i,j));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			sharkDir[i]=Integer.parseInt(st.nextToken())-1;
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<4; k++) {
					sharkPriority[i][j][k]=Integer.parseInt(st.nextToken())-1;
				}
			}
		}
		
		System.out.println(solve());
	}

	public static int solve() {
		int time=0;
		
		while(M>1) { // 1번상어만 남았을 때 끝남
			// 1000초가 넘었는데 아직 남아있는 상어가 2마리 이상이라면 -1출력
			if(time>=1000) return -1;
			// 1. 상어가 자기 자리에 냄새를 뿌린다.
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]>=0) {
						smellMap[i][j]=new Smell(time,map[i][j]); // 뿌린 시간과 뿌린 상어의 번호
					}
				}
			}

			// 2. 1초마다 모든 상어가 동시에 이동
			int[][] newMap = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(newMap[i], -1);
			}
			
			for(int i=0; i<q.size(); i++) {
				Shark now = q.poll();
				
				boolean find = false;
				int sharkNum = now.num;
				// 인접한 칸중 아무 냄새가 없는 칸 찾기
				for(int d=0; d<4; d++) {
					int nr = now.r+dr[sharkPriority[sharkNum][sharkDir[sharkNum]][d]];
					int nc = now.c+dc[sharkPriority[sharkNum][sharkDir[sharkNum]][d]];
					
					if(smellMap[nr][nc]==null || time - smellMap[nr][nc].time > K) { // 아무 냄새가 없는 칸이라면
						if(newMap[nr][nc]==-1) { // 다른 상어가 없는 칸이라면
							newMap[nr][nc]=sharkNum;
							find=true;
						}else { // 이미 다른 상어가 있다면
							if(newMap[nr][nc]>sharkNum) { // 다른 상어의 번호가 내 번호보다 크다면
								newMap[nr][nc]=sharkNum;
								find=true;
							}
						}
						break;
					}
				}
				
				if(find) continue; // 인접한 칸에서 갈 곳을 찾았다면 다음 단계는 pass
				else {
					//
				}
				
			}
			
			// 3. 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아있으면, 가장 작은 상어를 제외하고 나머지는 쫓겨난다.
			
			time++;
		}
		
		return time;
	}
}
