package boj.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj_17413 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		String s = br.readLine();
		boolean tagFlag=false;
		
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(c=='<') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				tagFlag=true;
				sb.append(c);
			}else if(c=='>') {
				
				tagFlag=false;
				sb.append(c);
			}else if(c==' ') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(' ');
			}else if(tagFlag){
				sb.append(c);
			}else {
				stack.add(c);
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb.toString());
	}
}
