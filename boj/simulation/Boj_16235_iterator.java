package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_16235_iterator {
	
	static int n,m,k;
	static int[][] map;
	static int[][] yangboon;
	
	// 왼상, 상, 오상, 왼, 오, 왼하, 하, 우하
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0 , 1, -1, 1, -1, 0, 1};
	
	static LinkedList<Tree> trees;
	static Queue<Tree> dead;

	static class Tree implements Comparable<Tree>{
		int r;
		int c;
		int age;
		
		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age-o.age;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		yangboon = new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				map[i][j]=5;
				yangboon[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		trees = new LinkedList<>();
		dead = new LinkedList<>();
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			trees.add(new Tree(
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken())
			));
		}
		
		Collections.sort(trees);
		
		simulation();
		
		System.out.println(trees.size());
	}
	
	static public void simulation() {
		int year=0;
		while(true) {
			if(year==k) return;
			
			// 봄
			Iterator<Tree> iterator = trees.iterator();
			while(iterator.hasNext()) {
				Tree tree = iterator.next();
				int r = tree.r;
				int c = tree.c;
				int age = tree.age;
				if(map[r][c]-age<0) {
					dead.offer(tree); // Queue의 offer, poll은 O(1)
					iterator.remove(); // iterator로 remove할 시 O(1)
				}else {
					map[r][c]-=age;
					tree.age+=1;
				}
			}
			
			// 여름
			while(!dead.isEmpty()) {
				Tree tree = dead.poll();
				map[tree.r][tree.c]+=tree.age/2;
			}
			
			// 가을
			LinkedList<Tree> addTree = new LinkedList<>(); // 그냥 trees에 넣었더니 ConcurrentModificationException뜸
			for(Tree tree : trees) {
				int r = tree.r;
				int c = tree.c;
				if(tree.age%5==0) {
					for(int d=0; d<8; d++) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						if(nr<1 || nr>n || nc<1 || nc>n) continue;
						addTree.add(new Tree(nr,nc,1));
					}
				}
			}
			trees.addAll(0,addTree);
			
			// 겨울
			for(int r=1; r<=n; r++) {
				for(int c=1; c<=n; c++) {
					map[r][c] += yangboon[r][c];
				}
			}
			
			year++;
		}
	}
}
