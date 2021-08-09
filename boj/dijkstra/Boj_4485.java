package boj.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_4485 {
	static int[][] map;
	static int[][] dijk;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N;
	
	static class P implements Comparable<P>{
		int row;
		int col;
		int cost;
		
		public P(int row, int col, int cost) {
			super();
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(P o) {
			return this.cost-o.cost;
		}
		
	}

	public static int dijkstra() {
		
		dijk[0][0]=map[0][0];
		
		PriorityQueue<P> pq = new PriorityQueue<>();
		pq.offer(new P(0,0,map[0][0]));
		
		while(!pq.isEmpty()) {
			P p = pq.poll();
			
			for(int d=0; d<4; d++) {
				int nr = p.row+dr[d];
				int nc = p.col+dc[d];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N) {
					if(dijk[nr][nc] > dijk[p.row][p.col]+map[nr][nc]) {
						dijk[nr][nc] = dijk[p.row][p.col]+map[nr][nc];
						pq.offer(new P(nr,nc,dijk[nr][nc]));
					}
				}
			}
		}
		return dijk[N-1][N-1];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int cnt=1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) {
				break;
			}
			
			map = new int[N][N];
			dijk = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					dijk[i][j]=987654321;
				}
			}
			
			System.out.println("Problem "+(cnt++)+": "+ dijkstra());
		}
	}

}
