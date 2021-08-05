package boj.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_2493 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] laser = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			laser[i]=Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> s = new Stack<>();
		int[] ans = new int[N];
		
		for(int i=0; i<N; i++) {
			w:while(true) {
				if(s.isEmpty()) {
					s.push(i);
					ans[i]=0;
					break w;
				}else {
					int temp=s.peek();
					if(laser[temp]>=laser[i]) { // 수신 가능
						ans[i]=temp+1;
						s.push(i);
						break w;
					}else { // 지금 temp 위치의 탑은 수신 불가능
						s.pop();
					}
				}
			}
		}
		for(int i : ans) {
			System.out.print(i+" ");
		}
	}
}
