package swea.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_1238 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T=10;
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int l = Integer.parseInt(st.nextToken());
			int start=Integer.parseInt(st.nextToken());
			
			int[][] map = new int[l+1][l+1];
			int[] visited = new int[l+1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<l; i=i+2) {
				int temp1=Integer.parseInt(st.nextToken());
				int temp2=Integer.parseInt(st.nextToken());
				
				map[temp1][temp2]=1;
			}
			
			Queue<Integer> q = new LinkedList<Integer>();
			
			q.add(start);
			visited[start]=1;
			int now=0;
			while(!q.isEmpty()) {
				now = q.poll();
				for(int i=1; i<l+1; i++) {
					if(map[now][i]==1 && visited[i]==0) {
						q.add(i);
						visited[i]=visited[now]+1;
					}
				}
			}
			
			int max=0;
			for(int i=1; i<=l; i++) {
				if(visited[i]==visited[now]) {
					max = Math.max(max, i);
				}
			}
			System.out.println("#"+t+" "+max);
		}
	}
}
