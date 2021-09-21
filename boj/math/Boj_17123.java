package boj.math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_17123 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N+1][N+1];
			
			int[] row = new int[N+1];
			int[] col = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int cnt=0;
				for(int j=1; j<=N; j++) {
					int num = Integer.parseInt(st.nextToken());
					cnt+=num;
					arr[i][j]=num;
				}
				row[i]=cnt;
			}
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					col[i]+=arr[j][i];
				}
			}
			// 연산 시작
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int r1 = Integer.parseInt(st.nextToken());
				int c1 = Integer.parseInt(st.nextToken());
				int r2 = Integer.parseInt(st.nextToken());
				int c2 = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				for(int r=r1; r<=r2; r++) {
					int c = c2-c1+1;
					row[r]+=c*v;
				}
				
				for(int c=c1; c<=c2; c++) {
					int r = r2-r1+1;
					col[c]+=r*v;
				}
			}
			
			for(int i=1; i<=N; i++) {
				bw.write(row[i]+" ");
			}
			bw.write("\n");
			for(int i=1; i<=N; i++) {
				bw.write(col[i]+" ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
}
