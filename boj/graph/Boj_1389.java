package boj.graph;

import java.util.Scanner;

public class Boj_1389 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(); // 유저 수
		int M = scan.nextInt(); // 친구 관계 수
		
		int[][] graph = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) {
					graph[i][j]=0;
				}
				else {
					graph[i][j] = 10000000;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			int one = scan.nextInt()-1;
			int two = scan.nextInt()-1;
			
			graph[one][two]=1;
			graph[two][one]=1;
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(graph[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					graph[i][j]=Math.min(graph[i][j], graph[i][k]+graph[k][j]);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int index = N+1;
		for(int i=0; i<N; i++) {
			int sum=0;
			for(int j=0; j<N; j++) {
				sum+=graph[i][j];
			}
			if(min>sum) {
				min = sum;
				index = i;
			}
		}
		
		System.out.println(index+1);
		
	}

}
