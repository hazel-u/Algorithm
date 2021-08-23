package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1260 {
	
	static int N,M,V;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			arr[from][to]=1;
			arr[to][from]=1;
		}
		
		boolean[] visited = new boolean[N+1];
		dfs(V,visited);
		System.out.println();
		bfs(V);
	}
	
	static void dfs(int current, boolean[] visited) {
		visited[current]=true;
		System.out.print(current+" ");
		
		for(int i=1; i<=N; i++) {
			if(arr[current][i]==1 && !visited[i]) {
				dfs(i, visited);
			}
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];

		q.add(start);
		visited[start]=true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current +" ");
			
			for(int i=1; i<=N; i++) {
				if(arr[current][i]==1 && !visited[i]) {
					q.add(i);
					visited[i]=true;
				}
			}
		}
	}
}
