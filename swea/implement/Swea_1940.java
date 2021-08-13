package swea.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_1940 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int move=0;
			int dist=0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				/*
				 	0 : 현재 속도 유지
				 	1 : 가속
				 	2 : 감속
				*/
				if(command==1) {
					int v = Integer.parseInt(st.nextToken());
					move+=v;
				}else if(command==2) {
					int v = Integer.parseInt(st.nextToken());
					move-=v;
					if(move<0) {
						move=0;
					}
				}
				dist+=move;
			}
			System.out.println("#"+t+" "+dist);
		}
	}

}
