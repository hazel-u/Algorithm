package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2578 {

	static class Pos{
		int r,c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int bingoCnt=0;
		
		Pos[] info = new Pos[26];
		int[][] dp = new int[5][5];
		
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				int num = Integer.parseInt(st.nextToken());
				info[num]=new Pos(i,j);
			}
		}
		
		boolean kflag = false;
		boolean lflag = false;
		// 빙고 시작
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				int num = Integer.parseInt(st.nextToken());
				int r = info[num].r;
				int c = info[num].c;
				dp[r][c]++;
				
				// 가로
				int asum=0;
				for(int a=0; a<5; a++) {
					asum+=dp[r][a];
				}
				if(asum==5) bingoCnt++;
				
				// 세로
				int bsum=0;
				for(int b=0; b<5; b++) {
					bsum+=dp[b][c];
				}
				if(bsum==5) bingoCnt++;
				
				// 대각선
				if(!kflag) {
					int ksum=0;
					for(int k=0; k<5; k++) {
						ksum+=dp[k][k];
					}
					if(ksum==5) {
						bingoCnt++;
						kflag=true;
					}
				}
				if(!lflag) {
					int lsum=0;
					for(int l=0; l<5; l++) {
						lsum+=dp[l][5-l-1];
					}
					if(lsum==5) {
						bingoCnt++;
						lflag=true;
					}
				}
				
				
				if(bingoCnt>=3) {
					System.out.println(i*5+j+1);
					return;
				}
			}
		}
	}
}
