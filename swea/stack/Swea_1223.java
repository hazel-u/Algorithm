package swea.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Swea_1223 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T=10;
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			
			Stack<Character> oper = new Stack<>();
			Stack<Integer> after = new Stack<>();
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<N; i++) {
				if(s.charAt(i)=='+') {
					if(oper.size()==0) oper.add(s.charAt(i));
					else {
						while(!oper.isEmpty()) {
							sb.append(oper.pop());
						}
						oper.add(s.charAt(i));
					}
				}else if(s.charAt(i)=='*') oper.add(s.charAt(i));
				else sb.append(s.charAt(i));
			}
			while(!oper.isEmpty()) sb.append(oper.pop());
			
			for(int i=0; i<sb.length(); i++) {
				if(sb.charAt(i)=='*') {
					int res = after.pop() * after.pop();
					after.add(res);
				}else if(sb.charAt(i)=='+') {
					int res = after.pop() + after.pop();
					after.add(res);
				}else after.add(sb.charAt(i)-'0');
			}
			
			System.out.println("#"+t+" "+after.pop());
		}
	}
}
