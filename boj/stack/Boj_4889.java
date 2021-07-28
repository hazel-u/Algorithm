package boj.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class Boj_4889 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int num=0;
		while(true){
			int cnt=0;
			num++;
			String s = br.readLine();
			if(s.contains("-")) {
				break;
			}
			
			int l = s.length();
			Stack<Character> stack = new Stack<>();
			
			for(int i=0; i<l; i++) {
				/*
				 	1. 닫는 괄호면 스택에서 여는 괄호를 하나 꺼내
				 		1-1. 스택이 비어있다면, 닫는 괄호를 여는 괄호로 바꾼 후 스택에 넣어(cnt++)
				 	2. 여는 괄호면 스택에 넣어
				 	3. 문자열을 다 돌았는데 스택에 아직 여는 괄호가 남아있다면, cnt=cnt+stack.size();
				*/
				
				if(s.charAt(i)=='}') {
					if(stack.isEmpty()) { // 1-1
						stack.push('{');
						cnt++;
					}else { // 1
						stack.pop();
					}
				}else if(s.charAt(i)=='{') { //2
					stack.push('{');
				}
			}
			if(!stack.isEmpty()) {
				cnt+=stack.size()/2;
			}
			sb.append(num+". "+cnt+"\n");
		}
		System.out.println(sb);
	}
	
}
