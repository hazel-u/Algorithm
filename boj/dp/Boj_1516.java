package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1516 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] list = new ArrayList[N+1];
		int[] indegree = new int[N+1];
		int[] value = new int[N+1];
		int[] result = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i]=new ArrayList<Integer>();
		}
		
		for(int n=1; n<=N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			value[n] = Integer.parseInt(st.nextToken()); // 각 건물을 짓는데 걸리는 시간
			
			// 건물을 짓기 위해 미리 지어져야하는 건물
			// temp는 어떤 건물이 지어질 때 내가 필요한건지 저장해놓는 곳
			while(true) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp==-1) break;
				indegree[n]++; // n이 지어지기 위해 몇개의 건물이 미리 지어져야 하는지
				list[temp].add(n);
			}
		}
		
		
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) { // 나는 혼자 지어질 수 있어
				q.add(i);
				result[i]=value[i];
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0; i<list[cur].size(); i++) { // cur를 필요로 하는 애들이 얼마나 있는지
				int next = list[cur].get(i);
				indegree[next]--;
				
				result[next]=Math.max(result[next], result[cur]+value[next]);
				//System.out.println(next);
				
				if(indegree[next]==0) {
					q.add(next);
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			System.out.println(result[i]);
		}
		
	}

}
