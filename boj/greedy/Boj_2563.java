package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2563 {
	static int[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		map=new int[100][100];
		
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
		
		int cnt=0;
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]==1) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
}
