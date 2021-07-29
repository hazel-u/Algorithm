package boj.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1920 {
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			int target = Integer.parseInt(st.nextToken());
			System.out.println(binary_search(target, 0, N-1));
		}
		
		
	}
	
	static int binary_search(int target, int start, int end) {
		while (start<=end) {
			int middle = (start+end)/2;
			if(A[middle]==target) {
				return 1;
			}else if(A[middle]<target) {
				start=middle+1;
			}else {
				end=middle-1;
			}
		}
		return 0;
	}

}
