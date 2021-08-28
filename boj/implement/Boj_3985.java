package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_3985 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int[] cake = new int[L+1];
		int[] eat = new int[N+1];
		
		int maxP=0;
		int maxCnt=0;
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			// 가장 많이 먹을거로 예상하는 사람
			int temp = end-start;
			if(maxCnt<temp) {
				maxCnt=temp;
				maxP=i;
			}
			
			
			for(int e=start; e<=end; e++) {
				if(cake[e]==0) {
					eat[i]++;
					cake[e]=i;
				}
			}
		}
		
		// 실제로 많이 먹은 사람
		int RmaxP=0;
		int RmaxCnt=0;
		for(int i=1; i<=N; i++) {
			if(RmaxCnt<eat[i]) {
				RmaxCnt=eat[i];
				RmaxP=i;
			}
		}
		
		System.out.println(maxP);
		System.out.println(RmaxP);
	}
}
