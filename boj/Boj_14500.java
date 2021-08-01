package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_14500 {
	static int r;
	static int c;
	static int[][] map;
	static int[][][] mino = {{{0,0},{0,1},{0,2},{0,3}}, {{0,0},{0,1},{1,0},{1,1}}, {{0,0},{1,0},{2,0},{2,1}}, 
			{{0,0},{1,0},{1,1},{2,1}}, {{0,0},{0,1},{0,2},{1,1}}};
	static int maxV=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map=new int[r][c];
		
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// map 90도 돌리기
		for(int i=0; i<4; i++) {
			map=rotate(map);
			maxV=Math.max(maxV, find(map));
		}
		
		
		// map 뒤집기
		maxV = Math.max(maxV, find(updown(map)));
		map = rotate(map); // 원래 map으로 돌리고
		// 한번 더 map 뒤집기
		maxV = Math.max(maxV, find(updown(map)));
		
		System.out.println(maxV);
		
	}
	
	
	
	static int[][] rotate(int[][] arr){
		int n = arr.length;
		int m = arr[0].length;
		int[][] rotate = new int[m][n];
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				rotate[i][j]=arr[n-1-j][i];
			}
		}
		return rotate;
	}
	
	static int[][] updown(int[][] arr){
		int n = arr.length;
		int m = arr[0].length;
		int[][] rotate = new int[n][m];
		
		for(int i=0; i<n/2+1; i++) {
			for(int j=0; j<m; j++) {
				rotate[i][j] = arr[n-i-1][j];
				rotate[n-i-1][j]=arr[i][j];
			}
		}
		
		return rotate;
	}

	static int find(int[][] arr) {
		int max=0;
		
		// map에 mino를 적용시켜서 가장 큰 값을 찾아 출력
		for(int mr=0; mr<r; mr++) {
			for(int mc=0; mc<c; mc++) { // map전체를 돌면서
				int cnt=0;
				for(int i=0; i<5; i++) { // mino 5가지 다 적용시켜
					for(int j=0; j<4; j++) {
						int ni = mr+mino[i][j][0];
						int nj = mc+mino[i][j][1];
						if(ni>0 && ni<r && nj>0 && nj<r) { // 범위 안에 들고
							cnt+=arr[ni][nj];
						}else { // 한칸이라도 범위 안에 안들면 나가
							cnt=0;
							break;
						}
					}
					max = Math.max(max, cnt);
				}
				
			}
		}
		
		return max;
	}
	
}
