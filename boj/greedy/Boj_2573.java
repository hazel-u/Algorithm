package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2573 {
	static int[][] map;
	static int max;
	
	public static boolean isBlack(int r, int c, int w, int h) {
		int i, j;
	    for (i = r; i <= r + h; ++i) {
	        for (j = c; j <= c + w; ++j) {
	            if (map[i][j] != 1)
	                return false;
	        }
	    }
		return true;
	}
	
	public static void check(int r, int c) {
		int w,h;
		
		// 가로크기 증가
		for(w=0; w<100; w++) {
			if((c+w)>=100 || map[r][c+w]==0) break;
			
			for(h=0; h<100; h++) {
				if((r+h)>=100 || map[r+h][c]==0) break;
				
				if(isBlack(r,c,w,h)) {
					max = Math.max(max, (w+1)*(h+1));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		map=new int[100][100];
		
		// 색종이 붙이기
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					if(y+j<100 && x+i<100) map[y+j][x+i] = 1;
				}
			}
		}
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]==1) {
					check(i,j);
				}
			}
		}
		
		System.out.println(max);
	}

}
