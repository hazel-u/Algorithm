package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7569 {
	
	static int[][][] box;

	static int X;
	static int Y;
	static int H;
	
	// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 (h,y,x)
	static int[] dh= {1,-1,0,0,0,0};
	static int[] dy= {0,0,0,0,-1,1};
	static int[] dx= {0,0,-1,1,0,0};
	


	public static void main(String[] args) throws IOException {
		//  가로 X  세로 Y	 높이 H
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		box = new int[H][Y][X]; // 높이, 세로, 가로
		
		Queue<Tomato> tq = new LinkedList<>();
		
		// 상자 채우기
		for(int h=0; h<H; h++) {
			for(int n=0; n<Y; n++) {
				st = new StringTokenizer(br.readLine());
				for(int m=0; m<X; m++) {
					box[h][n][m]=Integer.parseInt(st.nextToken());
					if(box[h][n][m]==1) {
						tq.add(new Tomato(h,n,m));
					}
				}
			}
		}
		
		/*
		 	익은 토마토 : 1
		 	익지 않은 토마토 : 0
		 	토마토가 없는 칸 : -1
		*/
		while(!tq.isEmpty()) {
			int tq_size=tq.size();
			while(tq_size>0) {
				tq_size--;
				
				Tomato t = tq.poll();
				int th = t.h;
				int ty = t.y;
				int tx = t.x;
				
				// 토마토 익히기
				for(int d=0; d<6; d++) {
					int new_h = th+dh[d];
					int new_y = ty+dy[d];
					int new_x = tx+dx[d];
					if((new_h>-1) && (new_h<H) && (new_y>-1) && (new_y<Y) && (new_x>-1) && (new_x<X) && box[new_h][new_y][new_x]==0) {
						box[new_h][new_y][new_x]=box[th][ty][tx]+1;
						tq.add(new Tomato(new_h, new_y, new_x));
					}
				}
			}
		}
		
		int day = Integer.MIN_VALUE;
		
		for(int h=0; h<H; h++) {
			for(int n=0; n<Y; n++) {
				for(int m=0; m<X; m++) {
					if(box[h][n][m]==0) {
						System.out.println(-1);
						return;
					}
					else if(box[h][n][m]>0) day = Math.max(day, box[h][n][m]);
				}
			}
		}
		if(day==1) System.out.println(0);
		else System.out.println(day-1);
	}

}

class Tomato{
	int h;
	int y;
	int x;
	
	Tomato(int h, int y, int x){
		this.h = h;
		this.y = y;
		this.x = x;
	}
	
}








