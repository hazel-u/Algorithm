package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17144 {
	
	static int R,C,T;
	static int[][] A;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static Queue<Dust> q;
	
	//공기 청정기 (r,c)
	static int[] first;
	static int[] second;
	
	static class Dust{
		int r;
		int c;
		int v;
		
		public Dust(int r, int c, int v) {
			super();
			this.r = r;
			this.c = c;
			this.v = v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		q = new LinkedList<>();
		A = new int[R][C];
		
		first = new int[2];
		second = new int[2];
		
		boolean firstFlag=false;
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				if(A[i][j]==-1) {
					if(!firstFlag) {
						first[0]=i;
						first[1]=j;
						A[i][j]=0;
						firstFlag=true;
					}else {
						second[0]=i;
						second[1]=j;
						A[i][j]=0;
					}
				}
				if(A[i][j]>0) q.add(new Dust(i,j,A[i][j]));
			}
		}
		
		start();
		
		// 남아있는 미세먼지 카운트
		int dust=0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				dust += A[r][c];
			}
		}

		System.out.println(dust);
	}
	
	static void start() {
		int time=0;
		
		while(true) {
			if(time==T) return;
			
			int[][] spread = new int[R][C];
			
			// 1. 미세먼지 확산
			while(!q.isEmpty()) {
				Dust cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				int v = cur.v;
				
				int total_use = 0;
				
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(nr<0 || nr>=R || nc<0 || nc>=C) continue;
					if(nr==first[0]&&nc==first[1]) continue;
					if(nr==second[0]&&nc==second[1]) continue;
					spread[nr][nc]+=(v/5);
					total_use+=(v/5);
				}
				A[r][c]=v-total_use;
			}
			// 확산된 미세먼지 A에 더하기
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					A[r][c]+=spread[r][c];
				}
			}
			
			// 2. 공기청정기 작동
			int[][] move = new int[R][C];
			for(int r=0; r<R; r++) move[r]=A[r].clone();
			
			// 위쪽 공기청정기
			for(int r=0; r<=first[0]; r++) {
				for(int c=0; c<C; c++) {
					if(c==0) {
						if(r==first[0]) continue;
						move[r+1][c]=A[r][c];
					}
					else if(r==0) move[r][c-1]=A[r][c];
					else if(c==C-1) move[r-1][c]=A[r][c];
					else if(r==first[0]) move[r][c+1]=A[r][c];
				}
			}
			move[first[0]][0]=0; // 공기청정기
			move[first[0]][1]=0; // 공기청정기에서 나온 공기
			
			// 아래쪽 공기청정기
			for(int r=second[0]; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(c==0) {
						if(r==second[0]) continue;
						move[r-1][c]=A[r][c];
					}
					else if(r==R-1) move[r][c-1]=A[r][c];
					else if(c==C-1) move[r+1][c]=A[r][c];
					else if(r==second[0]) move[r][c+1]=A[r][c];
				}
			}
			move[second[0]][0]=0; // 공기청정기
			move[second[0]][1]=0; // 공기청정기에서 나온 공기
			
			// move 배열 값 A배열로 옮기기
			for(int r=0; r<R; r++) A[r]=move[r].clone();
			
			// 미세먼지 큐에 넣기
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(A[r][c]>0) q.add(new Dust(r,c,A[r][c]));
				}
			}
			
			// 시간 추가
			time++;
		}
	}
}
