package swea.floydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_1263 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/*
		 
		 	4 0 0 1 0 0 0 1 0 1 1 0 1 0 0 1 0
		 	
		 	
		 	  0 1 2 3
		 	0 0 0 1 0
		 	1 0 0 1 0
		 	2 1 1 0 1
		 	3 0 0 1 0
		 	
		 	
		 	0 - 2 - 1 
		 	    l
		 	    3
		 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			int[] CC = new int[N];
			
			int[][] D = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					D[i][j]=Integer.parseInt(st.nextToken());
					if(i!=j && D[i][j]==0) D[i][j]=987654321;
				}
			}
			
			for(int k=0; k<N; k++) { // 경유지
				for(int i=0; i<N; i++) { // 출발지
					for(int j=0; j<N; j++) { // 도착지
						D[i][j] = Math.min(D[i][j], D[i][k]+D[k][j]);
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					CC[i]+=D[i][j];
				}
			}

			int minV=987654321;
			for(int i=0; i<N; i++) {
				if(CC[i]<minV) minV=CC[i];
			}
			
			bw.write("#"+t+" "+minV+"\n");
		}
		bw.flush();
	}
}
