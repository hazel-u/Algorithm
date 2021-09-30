package swea.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Swea_1249 {

	static int N;
	static int[][] map;
	static int[][] visited;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int time;
		public Node(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new int[N][N];
			for(int i=0; i<N; i++) Arrays.fill(visited[i], Integer.MAX_VALUE/2);
			
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = s.charAt(j)-'0';
				}
			}
			
			dijk();
			bw.write("#"+t+" "+visited[N-1][N-1]+"\n");
		}
		bw.flush();
	}

	public static void dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0,0,map[0][0])); // start
		visited[0][0]=0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N) {
					int ntime = cur.time+map[nr][nc];
					if(visited[nr][nc]>ntime) {
						visited[nr][nc]=ntime;
						pq.offer(new Node(nr,nc,visited[nr][nc]));
					}
				}
			}
		}
	}
}
