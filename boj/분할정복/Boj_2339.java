package boj.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2339 {

	static int N;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// 1 불순물, 2 보석 결정체, 0 빈 곳
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = solve(0,N,0,N,-1);
		
		if(answer==0) System.out.println(-1);
		else System.out.println(answer);
		
	}
	
	public static int solve(int startR, int endR, int startC, int endC, int dir) {
		int answer=0;	
		
		int dCnt=0; // 불순물 카운트
		int jCnt=0; // 보석 카운트
		
		for(int r=startR; r<endR; r++) {
			for(int c=startC; c<endC; c++) {
				if(map[r][c]==1) dCnt++;
				else if(map[r][c]==2) jCnt++;
			}
		}
		
		if(jCnt==0) return 0;
		else if(dCnt==0 && jCnt==1) return 1;
		
		for(int c=startC; c<endC; c++) {
			for(int r=startR; r<endR; r++) {
				if(map[r][c]==1) { // 불순물이 있는 곳이면 분할
					// 전에 가로로 자른게 아니면
					if(dir!=0) { 
						// 이번에 가로로 자른다.
						boolean possible=true;
						// 결정체가 있는 곳은 자를 수 없다.
						for(int _c=startC; _c<endC; _c++) {
							if(map[r][_c]==2) {
								possible=false;
								break;
							}
						}
						
						if(possible)answer += solve(startR, r, startC, endC, 0) * solve(r+1,endR, startC, endC, 0);
					}
					// 전에 세로로 자른게 아니면
					if(dir!=1) {
						// 이번에는 세로로 자른다.
						boolean possible=true;
						// 결정체가 있는 곳은 자를 수 없다.
						for(int _r=startR; _r<endR; _r++) {
							if(map[_r][c]==2) {
								possible=false;
								break;
							}
						}
						if(possible) answer += solve(startR, endR, startC, c, 1) * solve(startR, endR, c+1, endC,1);
					}
				}
			}
		}
		
		return answer;
	}
}
