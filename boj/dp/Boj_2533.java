package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_2533 {

	static int N;
	static ArrayList<Integer>[] list;
	static int[][] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][2];
		visited=new boolean[N+1];
		list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int n=0; n<N-1; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		dp(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	public static void dp(int index) {
		visited[index]=true;
		dp[index][0]=0; // index가 얼리어답터가 아닐 경우
		dp[index][1]=1; // index가 얼리어답터일 경우, 자기 자신 포함해야하니 1로 초기화
		
		for(int next : list[index]) { // index와 연결된 노드들을 돌면서
			if(visited[next]) continue; // 이미 방문한 곳이면 pass
			// 방문했던 곳이 아니라면
			dp(next); // 그 곳에서 다시 탐색
			dp[index][0] += dp[next][1]; // index가 얼리어답터가 아니면 주변 노드는 얼리어답터여야 한다.
			dp[index][1] += Math.min(dp[next][0], dp[next][1]); // index가 얼리어답터이면 주변노드가 얼리어답터가 아니어도 된다.
		}
	}

}
