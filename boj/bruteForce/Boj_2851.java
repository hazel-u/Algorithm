package boj.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2851 {

	static int n;
	static int[] arr;
	static boolean[] visited;
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=10;
		arr = new int[n];
		visited = new boolean[n];
		
		for(int i=0; i<n; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		subset(0);
		
		System.out.println(res);
	}

	static void subset(int cnt) {
		if(cnt==n) {
			int temp_sum=0;
			for(int i=0; i<n; i++) {
				if(visited[i]) temp_sum += arr[i];
				if(!visited[i]) break;
			}
			
			if(Math.abs(res-100)>Math.abs(temp_sum-100)) {
				res = temp_sum;
			}else if(Math.abs(res-100)==Math.abs(temp_sum-100)) {
				if(res<temp_sum) {
					res=temp_sum;
				}
			}

			return;
		}
		
		visited[cnt]=true;
		subset(cnt+1);
		visited[cnt]=false;
		subset(cnt+1);
	}
}
