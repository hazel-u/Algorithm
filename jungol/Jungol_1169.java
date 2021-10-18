package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_1169 {

	static int N,M;
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		visited = new boolean[7];
		
		/*
		 
		 	M
		 	1. 주사위를 N번 던져 나올 수 있는 모든 경우의 수
		 	2. 주사위를 N번 던져 중복이 되는 경우를 제거하고 나올 수 있는 경우의 수
		 	3. 주사위를 N번 던져 모두 다른 수가 나올 수 있는 모든 경우의 수
		 
		 */
		
		if(M==1) all(0);
		else if(M==2) noRepeat(0,1);
		else if(M==3) allDifferent(0);
		
	}

	public static void all(int cnt) {
		if(cnt==N) {
			for(int i=0; i<N; i++) {
				System.out.print(arr[i]+" ");
			}
			System.out.println();
			
			return;
		}
		
		for(int i=1; i<=6; i++) {
			arr[cnt]=i;
			all(cnt+1);
		}
	}
	
	public static void noRepeat(int cnt, int start) {
		// combination
		if(cnt==N) {
			for(int i=0; i<N; i++) {
				System.out.print(arr[i]+" ");
			}
			System.out.println();
			
			return;
		}
		
		for(int i=start; i<=6; i++) {
			arr[cnt]=i;
			noRepeat(cnt+1, i);
		}
	}
	
	public static void allDifferent(int cnt) {
		if(cnt==N) {
			for(int i=0; i<N; i++) {
				System.out.print(arr[i]+" ");
			}
			System.out.println();
			
			return;
		}
		
		for(int i=1; i<=6; i++) {
			if(visited[i]) continue;
			arr[cnt]=i;
			visited[i]=true;
			allDifferent(cnt+1);
			visited[i]=false;
		}
		
	}
}
