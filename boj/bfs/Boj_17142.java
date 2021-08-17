package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17142 {
	
	static int[][] original_map;
	static Virus[] comV;
	static ArrayList<Virus> v;
	static int N,M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int min=98765421;
	
	
	static class Virus{
		int r,c,cnt;

		public Virus(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Virus [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		original_map = new int[N][N];
		v = new ArrayList<>();
		// 벽 -1, 비활성화 바이러스 -2, 활성화 바이러스 -3
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp==2) {
					original_map[i][j]=-2;
					v.add(new Virus(i,j,1));
				}else if(temp==1) {
					original_map[i][j]=-1;
				}else {
					original_map[i][j]=0;
				}
			}
		}
		
		comV = new Virus[M];
		combination(0,0);
		
		if(min==987654321) {
			System.out.println(-1);
		}else System.out.println(min);
		
	}
	
	private static void combination(int cnt, int start) {
		if(cnt==M) {
			int[][] map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]=original_map[i][j];
				}
			}
			bfs(map);
			return;
		}
		
		for(int i=start; i<v.size(); i++) {
			comV[cnt] = v.get(i);
			combination(cnt+1, i+1);
		}
	}
	
	private static void bfs(int[][] map) {
		Queue<Virus> vq = new LinkedList<>();
		
		System.out.println(Arrays.toString(comV));
		
		for(int i=0; i<comV.length; i++) {
			map[comV[i].r][comV[i].c]=-3;
			vq.offer(comV[i]);
		}
		
		while(!vq.isEmpty()) {
			Virus temp_v = vq.poll();
			if(map[temp_v.r][temp_v.c]==0) {
				map[temp_v.r][temp_v.c]=temp_v.cnt;
			}
			
			for(int d=0; d<4; d++) {
				if(temp_v.r+dr[d]>=0 && temp_v.r+dr[d]<N && temp_v.c+dc[d]>=0 && temp_v.c+dc[d]<N) { // 범위 안에 있고
					if(map[temp_v.r+dr[d]][temp_v.c+dc[d]]==0 || map[temp_v.r+dr[d]][temp_v.c+dc[d]]==-2) { // 빈 칸 혹은 비활성화된 바이러스면
						vq.offer(new Virus(temp_v.r+dr[d], temp_v.c+dc[d], temp_v.cnt+1));
					}
				}
			}
			
		}
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0) { // 바이러스가 퍼지지 못한 곳이 있다.
					return;
				}else {
					max=Math.max(max, map[i][j]-1);
				}
			}
		}
		min=Math.min(min, max);
		return;
	}
}


/*
5 1
2 2 2 1 1
2 1 1 1 1
2 1 1 1 1
2 1 1 1 1
2 2 2 2 0

위 테케 사용 시 dfs의 while문을 탈출 못함
*/
