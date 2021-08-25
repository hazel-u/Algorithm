package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16236 {
	
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}}; //상하우좌
	
	static int N;
	
	static int[][] map;
	static boolean[][] visited;
	
	static int moveCnt;
	
	static class Shark{
		int row, col;
		int size;
		int depth;
		int eatCnt;
		
		public Shark(int row, int col, int size, int depth, int eatCnt) {
			super();
			this.row = row;
			this.col = col;
			this.size = size;
			this.depth = depth;
			this.eatCnt = eatCnt;
		}
		
		public void eat(int cnt) {
			eatCnt++;
			if(eatCnt==size) {
				eatCnt=0;
				size++;
			}
		}
	}
	
	static class Fish implements Comparable<Fish>{
		int row, col;
		int size;
		int depth;
		
		public Fish(int row, int col, int size, int depth) {
			super();
			this.row = row;
			this.col = col;
			this.size = size;
			this.depth = depth;
		}

		@Override
		public int compareTo(Fish o) {
			if(depth == o.depth) {
				if(row==o.row) {
					return Integer.compare(col, o.col);
				}else {
					return Integer.compare(row, o.row);
				}
			}else {
				return Integer.compare(depth, o.depth);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		Shark shark = null;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					map[i][j]=0;
					shark = new Shark(i,j,2,0,0);
				}
			}
		}
		
		bfs(shark);
		
		System.out.println(moveCnt);
	}
	
	static void bfs(Shark shark) {
		Queue<Shark> q = new LinkedList<>();
		
		q.add(shark);
		
		visited=new boolean[N][N];
		visited[shark.row][shark.col]=true;
		
		// 상어와 가장 가까운 물고기의 depth
		int targetDepth = -1;
		
		PriorityQueue<Fish> targetFishList = new PriorityQueue<>();
		
		Shark front=null;
		
		findFish: while(!q.isEmpty()){
			front = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = front.row + dir[d][0];
				int nc = front.col + dir[d][1];
				
				if(isIn(nr,nc) && !visited[nr][nc]) {
					visited[nr][nc]=true;
					
					// 크기가 상어와 같거나 0이면 이동/통과 가능
					if(map[nr][nc]==0 || map[nr][nc]==front.size) {
						q.add(new Shark(nr,nc,front.size, front.depth+1, front.eatCnt));
					}
					// 먹을 수 있는 물고기가 발견되었다면 이동하고 먹이 후보에 넣기
					else if(map[nr][nc]<front.size) {
						// 처음 발견한 물고기(targetDepth==-1)이거나 depth가 처음 잡은 애랑 같으면
						if(targetDepth==-1 || targetDepth == front.depth+1) {
							Fish fish = new Fish(nr,nc,map[nr][nc],front.depth+1);
							targetFishList.add(fish);
							targetDepth = fish.depth;
						}
						// depth가 더 큰 물고기가 발견되면 더 이상 돌 필요없음
						else {
							break findFish;
						}
					}
				}
			}
		}
		
		// 먹을 물고기가 없으면 종료
		if(targetFishList.isEmpty()) return;
		// 물고기가 있으면 먹자
		else {
			Fish forEat = targetFishList.poll();
			
			front.eat(forEat.size);
			map[forEat.row][forEat.col]=0;
			moveCnt += forEat.depth;
			bfs(new Shark(forEat.row, forEat.col, front.size, 0, front.eatCnt));
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0<=r && 0<=c && r<N && c<N;
	}
}
