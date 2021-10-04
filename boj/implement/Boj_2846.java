package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2846 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int start=0;
		
		int max=0;
		
		for(int i=1; i<N; i++) {
			if(arr[i]<=arr[i-1]) {
				max=Math.max(max, arr[i-1]-arr[start]);
				start=i;
			}
		}
		max = Math.max(max,arr[N-1]-arr[start]);
		System.out.println(max);
	}

}
