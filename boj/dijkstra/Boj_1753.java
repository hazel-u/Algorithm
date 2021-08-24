package boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1753 {

	static int V,E;
	static int[] distance;
	static ArrayList<Vertex>[] list;
	
	static class Vertex implements Comparable<Vertex>{
		int now,dist;

		public Vertex(int now, int dist) {
			super();
			this.now = now;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.dist-o.dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		
		list = new ArrayList[V+1];
		distance = new int[V+1];
		
		Arrays.fill(distance, 987654321);
		
		for(int i=1; i<=V; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].add(new Vertex(to, weight));
		}
		
		dijkstra(start);
		
		for(int i=1; i<=V; i++) {
			if(i==start) System.out.println(0);
			else if(distance[i]==987654321) System.out.println("INF");
			else System.out.println(distance[i]);
		}
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		q.offer(new Vertex(start,0));
		distance[start]=0;
		
		while(!q.isEmpty()) {
			Vertex cur = q.poll();
			if(distance[cur.now]<cur.dist) continue;
			
			for(Vertex v : list[cur.now]) {
				if(distance[v.now]>distance[cur.now]+v.dist) {
					distance[v.now]=distance[cur.now]+v.dist;
					q.offer(new Vertex(v.now, distance[v.now]));
				}
			}
		}
	}
}
