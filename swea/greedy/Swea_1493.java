package swea.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_1493 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = 301;
		int[] start = new int[N];
		int[][] arr = new int[N][N];
		start[1]=1;
		arr[1][1]=start[1];
		for(int i=2; i<N; i++) {
			start[i]=start[i-1]+i-1;
			arr[i][1]=start[i];
		}
		
		int scnt=1;
		for(int i=1; i<N; i++) {
			scnt++;
			int cnt=scnt;
			for(int j=2; j<N; j++) {
				arr[i][j]=arr[i][j-1]+cnt;
				cnt++;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		// 테스트 시작
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			int x1=0;
			int y1=0;
			f1:for(int i=1; i<N; i++) {
				if(n1<start[i]) {
					for(int c=0; c<N; c++) {
						if(arr[i-1][1]+c==n1) {
							x1 = c+1;
							y1 = (i-1)-c;
							break f1;
						}
					}
				}
			}
			
			int x2=0;
			int y2=0;
			f2:for(int i=1; i<N; i++) {
				if(n2<start[i]) {
					for(int c=0; c<N; c++) {
						if(arr[i-1][1]+c==n2) {
							x2 = c+1;
							y2 = (i-1)-c;
							break f2;
						}
					}
				}
			}
			bw.write("#"+t+" "+arr[y1+y2][x1+x2]+"\n");
		}
		bw.flush();		
	}
}
