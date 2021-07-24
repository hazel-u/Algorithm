package boj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj_2252 {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		
		int[] inDegree = new int[N+1];
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			int one = scan.nextInt();
			int two = scan.nextInt();
			
			list[one].add(two);
			inDegree[two]++;
		}
		
		// Topological Sorting
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=1; i<N+1; i++) {
			if(inDegree[i]==0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			System.out.print(q.peek()+" ");
			
			int now = q.poll();
			
			for(int i=0; i<list[now].size(); i++) {
				int next = list[now].get(i);
				inDegree[next]--;
				
				if(inDegree[next]==0) {
					q.add(next);
				}
			}
		}
		
	}

}
