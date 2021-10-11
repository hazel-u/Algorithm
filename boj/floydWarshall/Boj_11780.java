package boj.floydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_11780 {

	static int N,M;
	static int[][] bus;
	static int[][] path;
	static int INF = 987654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		bus = new int[N+1][N+1];
		path = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) bus[i][j]=0;
				else bus[i][j]=INF;
				path[i][j]=INF;
			}
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			bus[a][b]=Math.min(bus[a][b], c);
			path[a][b]=a;
		}
		
		// 플로이드 워샬
		for(int k=1; k<=N; k++) {
			for(int s=1; s<=N; s++) {
				for(int e=1; e<=N; e++) {
					if(bus[s][e]> bus[s][k]+bus[k][e]) {
						bus[s][e] = bus[s][k]+bus[k][e];
						path[s][e]=path[k][e];
					}
				}
			}
		}
		
		// 출력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(bus[i][j]>=INF) bus[i][j]=0;
				bw.write(bus[i][j]+" ");
			}
			bw.write("\n");
		}
		
		Stack<Integer> stack = new Stack<>();
		// 경로 출력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(path[i][j]==INF) bw.write("0"+"\n");
				else {
					int prev = j;
					stack.push(j);
					while(i!=path[i][prev]) {
						prev = path[i][prev];
						stack.push(prev);
					}
					bw.write((stack.size()+1)+" ");
					bw.write(i+" ");
					while(!stack.isEmpty()) bw.write(stack.pop()+" ");
					bw.write("\n");
				}
			}
		}
		bw.flush();
	}
}
