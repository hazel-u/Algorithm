package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1405 {
	static double[] percent;
	static int N;
	static int[][] map;
	static double answer;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		map=new int[30][30];
		
		N=Integer.parseInt(st.nextToken());
		percent = new double[4];
		
		for(int i=0; i<4; i++) {
			percent[i]=Double.parseDouble(st.nextToken())*0.01;
		}

		map[15][15]=1;
		dfs(15,15,0,1);
		
		System.out.println(answer);
		
	}
	
	public static void dfs(int r, int c, int cnt, double ep) {
		if(cnt==N) {
			answer+=ep;
			return;
		}
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr<0 || nr>=30 || nc<0 || nc>=30) continue;
			
			if(map[nr][nc]==0) {
				map[nr][nc]=1;
				dfs(nr, nc, cnt+1, ep*percent[d]);
				map[nr][nc]=0;
			}
		}
	}
}
