package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Boj_4179 {

	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		Queue<pos> jq = new LinkedList<>(); // 지훈이 위치 담는 큐
		Queue<pos> fq = new LinkedList<>(); // 불 위치 담는 큐
		
		
		for(int i=0; i<R; i++) {
			String in = br.readLine();
			for(int j=0; j<C; j++) {
				/*
				 	벽 : -1
				 	지나갈 수 있는 공간 : 0
				 	지훈이 초기위치 : 1
				 	불 : -2
				 */
				char ch = in.charAt(j);
				if(ch == '#') {
					map[i][j] = -1;
				}else if(ch=='.') {
					map[i][j] = 0;
				}else if(ch=='J') { // 지훈이
					map[i][j]=1;
					jq.add(new pos(i,j));
				}else { // 불
					map[i][j]=-2;
					fq.add(new pos(i,j));
				}
			}
		}
		
		int time = 0;
		while(true) {
			time++;
			// 불
			int fl = fq.size();
			while(fl>0) {
				fl--;
				pos fp = fq.poll();
				int fr = fp.r;
				int fc = fp.c;
				
				// 불확장
				for(int d=0; d<4; d++) {
					if((fr+dr[d])>-1 && (fr+dr[d])<R && (fc+dc[d])>-1 && (fc+dc[d])<C) { // 범위 안에 있고
						// 그 위치가 비어있는 곳이면
						if(map[fr+dr[d]][fc+dc[d]]>=0) {
							map[fr+dr[d]][fc+dc[d]]=-2;
							fq.add(new pos(fr+dr[d], fc+dc[d]));
						}
					}
				}
			}
			
			// 지훈
			int jl = jq.size();
			while(jl>0) {
				jl--;
				
				pos jp = jq.poll();
				int jr = jp.r;
				int jc = jp.c;
				
				// 지훈이 움직이기
				for(int d=0; d<4; d++) {
					// 탈출하면
					if(jr+dr[d] < 0 || jr+dr[d] >=R || jc+dc[d]<0 || jc+dc[d]>=C) {
						System.out.println(time);
						return;
					}
					// 탈출하지 못하면
					if(map[jr+dr[d]][jc+dc[d]]==0) { // 지나갈 수 있는 자리를 찾아서
						jq.add(new pos(jr+dr[d], jc+dc[d]));
						map[jr+dr[d]][jc+dc[d]]=1; // 지훈이 위치
					}
				}
			}
			
			
			if(jq.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
		}
	}
}


class pos{
	int r;
	int c;
	
	public pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

