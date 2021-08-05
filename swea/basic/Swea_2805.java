package swea.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Swea_2805 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int gap = N/2;
			
			int[][] map = new int[N][N];
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j]=s.charAt(j)-'0';
				}
			}
			int cnt=0;
			int res=0;
			while(cnt<N) {
				for(int i=0; i<N-(Math.abs(gap)*2); i++) {
					res+=map[cnt][Math.abs(gap)+i];
				}
				gap--;
				cnt++;
			}
			bw.write("#"+(t+1)+" "+res+"\n");
		}
		bw.flush();
	}
}