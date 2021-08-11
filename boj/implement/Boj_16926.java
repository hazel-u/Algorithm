package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16926 {
	static int N, M, R;
	static int map[][];
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int group = Math.min(N, M)/2; // 돌릴 그룹 갯수
		
		for(int count=0; count<R; count++) {
			for(int g=0; g<group; g++) { // (0,0), (1,1), ...
				int r=g;
				int c=g;
				
				int val = map[r][c];
				int d=0;
				while(d<4) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					if(nr>=g && nc>=g && nr<N-g && nc<M-g) {
						map[r][c]=map[nr][nc];
						r = nr;
						c = nc;
					}else d++;
				}
				map[r+1][c]=val;
			}
		}
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
