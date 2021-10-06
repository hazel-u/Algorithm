package jungol.슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jungol_2577 {

	static int N, D, K, C;
	static int ans=0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 접시 수
		int D = Integer.parseInt(st.nextToken()); // 초밥 가지수
		int K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int C = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		int[] sushi = new int[N];
		for(int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		int[] sCnt = new int[D+1];
		int cnt=0;
		// 맨 처음 상황
		for(int i=0; i<K; i++) {
			if(sCnt[sushi[i]]++==0) cnt++;
		}
		
		// K번 부터 돌기
		for(int i=1; i<N; i++) {
			if(--sCnt[sushi[i-1]]==0) cnt--;
			
			if(sCnt[sushi[(i+K-1)%N]]++==0) cnt++;
			
			// 쿠폰 넣기
			int tempCnt=cnt;
			if(sCnt[C]==0) tempCnt++;
			
			ans = Math.max(ans, tempCnt);
			if(ans==K+1) break;
		}
		
		System.out.println(ans);
	}
}
