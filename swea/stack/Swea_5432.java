package swea.stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Swea_5432 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String s = br.readLine();
			int sum=0;
			int now=0;
			int i=0;
			
			Stack<Character> stack = new Stack<>();
			while(i<s.length()) {
				if(s.charAt(i)=='(' && s.charAt(i+1)==')') {
					now=stack.size();
					sum+=now;
					i=i+2;
				}else if(s.charAt(i)=='(') {
					stack.push(s.charAt(i));
					i++;
				}else if(s.charAt(i)==')') {
					stack.pop();
					sum++;
					i++;
				}
			}
			System.out.println("#"+t+" "+sum);
		}
	}
}
