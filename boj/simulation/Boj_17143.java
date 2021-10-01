package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17143 {

	static int R,C,M;
	static Shark[][] map;
	
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,1,-1};
	
	static class Shark{
		int r;
		int c;
		int speed;
		int dir;
		int size;
		public Shark(int r, int c, int speed, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R+1][C+1];
		
		if(M==0) {
			System.out.println(0);
			System.exit(0);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			map[r][c] = new Shark(r,c,speed,dir,size);
		}
		
		System.out.println(solve());
	}
	
	public static int solve() {
		int angler = 0; // 낚시꾼 위치
		int total=0;
		
		Shark[][] tempMap = null;
		
		while(true) {
			// 1. 낚시꾼 이동
			angler++;
			if(angler>C) return total;
			
			// 2. 같은 열에 있는 상어 중 땅과 가장 가까운 상어 잡기
			for(int r=1; r<=R; r++) {
				if(map[r][angler]!=null) {
					total+=map[r][angler].size;
					map[r][angler]=null;
					break;
				}
			}
			
			// 3. 상어 이동
			tempMap = new Shark[R+1][C+1];
			for(int i=1; i<=R; i++) {
				for(int j=1; j<=C; j++) {
					if(map[i][j]!=null) {
						int nr = map[i][j].r;
						int nc = map[i][j].c;
						int speed = map[i][j].speed;
						int dir = map[i][j].dir;
						int size = map[i][j].size;
						
						if(dir==1 || dir==2) {
							int dist = speed % ((R-1)*2);
							
							for(int d=0; d<dist; d++) {
								if(nr==1) dir=2;
								else if(nr==R) dir=1;
								nr+=dr[dir];
							}
						}else if(dir==3 || dir==4) {
							int dist = speed % ((C-1)*2);
							
							for(int d=0; d<dist; d++) {
								if(nc==1) dir=3;
								else if(nc==C) dir=4;
								nc+=dc[dir];
							}
						}
						
						if(tempMap[nr][nc]!=null) { // 그 자리에 이미 상어가 있다면
							if(tempMap[nr][nc].size > size) { // 먼저 온 상어의 크기가 나의 크기보다 크다면
								continue;
							}else if(tempMap[nr][nc].size < size){ // 나보다 크기가 작다면
								tempMap[nr][nc] = new Shark(nr,nc,speed,dir,size);
							}
						}else {
							tempMap[nr][nc] = new Shark(nr,nc,speed,dir,size);
						}
					}
				}
			}
			
			// map에 tempMap복사
			map=tempMap;
		}
	}
}
