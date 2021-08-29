package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1592 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		arr[0]=1;
		
		int getB=0;
		int cnt=0;
		while(true) {
			cnt++; // 공던진 횟수
			int next=-1;
			if(arr[getB]%2==1) { // 홀수일 때
				next = (getB+L)%N;
			}
			else if(arr[getB]%2==0) { // 짝수일 때
				next = (getB-L)%N;
				if(next<0) {
					next = N+next;
				}
			}
			
			arr[next]++;
			if(arr[next]==M) {
				break;
			}
			getB=next;
		}
		System.out.println(cnt);
	}
}
