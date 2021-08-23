package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author 유혜승
 * https://www.acmicpc.net/problem/14226
 * [백준 14226] 이모티콘 
 *
 */
public class Boj_14226 {
	static int S;
	
	static class Status{
		int screen;
		int clip;
		int time;
		
		public Status(int screen, int clip, int time) {
			super();
			this.screen = screen;
			this.clip = clip;
			this.time = time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = Integer.parseInt(br.readLine());
		
		bfs();
	}
	
	private static void bfs() {
		Queue<Status> q = new LinkedList<>();
		boolean[][] visited = new boolean[1001][1001]; // screen, clip
		
		q.offer(new Status(1,0,0));
		visited[1][0]=true;
		
		while(!q.isEmpty()) {
			Status cur = q.poll();
			
			if(cur.screen==S) {
				System.out.println(cur.time);
				return;
			}
			
			// 1. 화면에 있는 이모티콘 복사
			if(!visited[cur.screen][cur.screen]) {
				q.offer(new Status(cur.screen, cur.screen, cur.time+1));
				visited[cur.screen][cur.screen]=true;
			}
			
			// 2. 복사한 이모티콘 화면에 붙여넣기
			if(cur.clip>0 && cur.screen+cur.clip<1001 && !visited[cur.screen+cur.clip][cur.clip]) {
				q.offer(new Status(cur.screen+cur.clip, cur.clip, cur.time+1));
				visited[cur.screen+cur.clip][cur.clip]=true;
			}
			
			// 3. 화면에 있는 이모티콘 한개 빼기
			if(cur.screen-1>0 && !visited[cur.screen-1][cur.clip]) {
				q.offer(new Status(cur.screen-1, cur.clip, cur.time+1));
				visited[cur.screen-1][cur.clip]=true;
			}
		}
	}
}
