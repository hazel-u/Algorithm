package jungol.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1681 {

	static int N;
	static int[][] cost;
	static boolean[] visited;
	static int min = 987654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=1; j<=N; j++) {
				cost[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		go(0, 1, 0);
		System.out.println(min);
	}

	static void go(int vCnt, int now, int totalCost) {
		visited[now]=true;
		
		if(vCnt==N-1) {
			if(cost[now][1]==0) return; // 회사로 가는 길을 모른다면 그냥 return
			
			totalCost += cost[now][1]; // 회사로 돌아가는 비용 추가
			min = Math.min(min, totalCost);
			
			return;
		}
		
		for(int i=2; i<=N; i++) {
			
			if(!visited[i] && totalCost<min && cost[now][i]!=0) {
				go(vCnt+1, i, totalCost+cost[now][i]);
				visited[i]=false;
			}
		}
	}
}

//5
//0 14 4 10 20
//14 0 7 8 7
//4 5 0 7 16
//11 7 9 0 2
//18 7 17 4 0
