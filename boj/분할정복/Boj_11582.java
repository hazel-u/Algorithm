package boj.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11582 {
	
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int k = Integer.parseInt(br.readLine());
		
		int p = N/2;
		while(true) {
			// 정렬 
			for(int i=0; i<N; i=i+(N/p)) {
				swap(i, i+(N/p)-1);
			}
			
			// 사람 수 확인
			if(p==k) {
				break;
			}
			p=p/2;
		}

		for(int i=0; i<N; i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	private static void swap(int start, int end) {
		int[] temp = new int[end-start+1];
		for(int i=start; i<end+1; i++) {
			temp[i-start]=arr[i];
		}
		
		Arrays.sort(temp);
		
		for(int i=start; i<end+1; i++) {
			arr[i]=temp[i-start];
		}
	}
}
