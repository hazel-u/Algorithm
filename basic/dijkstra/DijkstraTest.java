package basic.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DijkstraTest {
	
	static int V,E;
	static int[] dist;
	static ArrayList<Pos>[] arr;
	
	static class Pos implements Comparable<Pos>{
		int v;
		int cost;
		public Pos(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}
		@Override
		public int compareTo(Pos o) {
			return this.cost-o.cost;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); // 노드 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수
		
		int start = Integer.parseInt(br.readLine()); // 시작 노드
		
		dist = new int[V+1];
		Arrays.fill(dist, 987654321);
		
		arr = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			arr[i]=new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			arr[s].add(new Pos(e,c));
		}
		
		dijkstra(start);
		dist[start]=0;
		
		for(int i=1; i<=V; i++) {
			System.out.print(dist[i]+" ");
		}
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		q.add(new Pos(start, 0));
		
		while(!q.isEmpty()){
			Pos cur = q.poll();
			int now = cur.v;
			int cost = cur.cost;
			
			for(Pos p : arr[now]) {
				if(cost+p.cost < dist[p.v]) {
					dist[p.v]=cost+p.cost;
					q.add(new Pos(p.v,p.cost+cost));
				}
			}
		}
	}
}
