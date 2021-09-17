package boj.floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_9205 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int storeCnt = Integer.parseInt(br.readLine()); // 편의점 갯수
			
			
			int[][] info = new int[storeCnt+2][2]; // x,y
			
			int[][] D = new int[storeCnt+2][storeCnt+2]; // 각 노드별 거리를 저장
			for(int i=0; i<storeCnt+2; i++) {
				Arrays.fill(D[i], 987654321);
			}

			for(int i=0; i<storeCnt+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				info[i][0] = Integer.parseInt(st.nextToken());
				info[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 갈 수 있는 곳만 저장
			for(int i=0; i<storeCnt+2; i++) {
				for(int j=0; j<storeCnt+2; j++) {
					if(i==j) D[i][j]=0;
					else {
						int dist = Math.abs(info[i][0]-info[j][0])+Math.abs(info[i][1]-info[j][1]);
						if(dist <= 1000) {
							D[i][j]=dist;
						}
					}
				}
			}
			
			// 플로이드 워셜
			for(int k=0; k<storeCnt+2; k++) {
				for(int i=0; i<storeCnt+2; i++) {
					for(int j=0; j<storeCnt+2; j++) {
						D[i][j] = Math.min(D[i][j], D[i][k]+D[k][j]);
					}
				}
			}
			
			if(D[0][storeCnt+2-1]!=987654321) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
			
		}
	}
}
