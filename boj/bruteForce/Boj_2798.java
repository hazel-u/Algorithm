package boj.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2798 {

	static int N, M;
	static int[] arr;
	static int[] res;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		res = new int[3];
		
		st = new StringTokenizer(br.readLine());
		
		int i=0;
		while(st.hasMoreTokens()) {
			arr[i++]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		combination(0,0);
		
		System.out.println(result);
	}

	static void combination(int cnt, int start) {
		if(cnt==3) {
			int sum=0;
			for(int i=0; i<3; i++) {
				sum+=res[i];
			}
			
			if(sum<=M) {
				result = Math.max(result, sum);
			}
			return;
		}
		
		for(int i=start; i<N; i++) {
			res[cnt]=arr[i];
			combination(cnt+1, i+1);
		}
	}
}
