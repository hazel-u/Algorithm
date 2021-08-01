package boj.다시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int count = Integer.bitCount(N); // 기존 N으로 만들 수 있는 최소의 물병 갯수
		
		if(count <= K) {
			System.out.println(0);
			return;
		}
		
		int p = (int)Math.pow(2, Integer.toBinaryString(N).length()-1);
		
		int left = N-p;
		int newB = 0;
		
		while(count > K && p > left) {
			left++;
			newB++;
			count = Integer.bitCount(left)+1; // +1은 기존 N으로 만든 최소의 물병 갯수
		}
		
		System.out.println(newB);
	}

}
