package boj.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2056_위상정렬 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
			a.add(new ArrayList<>());
		}
		
		int[] indegree = new int[N+1];
		int[] time = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<num; j++) {
				// temp -> i 이런 구조 (i전에 temp가 선행되어야하니까)
				int temp = Integer.parseInt(st.nextToken());
				a.get(temp).add(i);
				
				indegree[i]++;
			}
			
		}
		
		System.out.println(topologicalSort(N,a,indegree,time));
	}
	
	public static int topologicalSort(int N, ArrayList<ArrayList<Integer>> a, int[] indegree, int[] time) {
		int ans=0;
		
		Queue<Integer> q = new LinkedList<>(); // indegree가 0인 노드를 넣는 큐
		int[] result = new int[N+1]; // 각 노드가 작업을 수행하는데 걸리는 시간
		
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) q.offer(i);
			result[i]=time[i];
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : a.get(now)) {
				indegree[next]--;
				
				result[next]=Math.max(result[next], result[now]+time[next]);
				
				if(indegree[next]==0) {
					q.offer(next);
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			ans=Math.max(ans, result[i]);
		}
		
		
		return ans;
	}

}
