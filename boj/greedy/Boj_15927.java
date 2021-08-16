package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_15927 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray();
		int sl = s.length;
		
		boolean flag=true;
		
		// 모두 같은 문자일 경우
		for(int i=0; i<sl; i++) {
			if(s[0]!=s[i]) flag=false;
		}
		if(flag) {
			System.out.println("-1");
			return;
		}
		
		// 회문 확인
		int start = 0;
		int end = sl-1;
		flag=true;
		while(start<end){
			if(s[start]==s[end]) {
				start++;
				end--;
			}else {
				flag=false;
				break;
			}
		}
		
		if(!flag) {
			System.out.println(sl); // 회문이 아닐 경우
		}else System.out.println(sl-1); // 회문일 경우, 현재 문자에 -1개를 구하면 회문이 아니게 된다.
	}
}
