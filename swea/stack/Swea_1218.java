package swea.stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Swea_1218 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t=1; t<=10; t++) {
			Stack<Character> s = new Stack<>();
			
			int len = Integer.parseInt(br.readLine());
			String str = br.readLine();
			int res = 1;
			if(len%2==1) {
				res=0;
			}else {
				f:for(int i=0; i<len; i++) {
					char c = str.charAt(i);
					
					if(c=='(' || c=='{' || c=='[' || c=='<') {
						s.push(c);
					}else {
						if(c==')' && s.peek()=='(') {
							s.pop();
						}else if(c=='}' && s.peek()=='{') {
							s.pop();
						}else if(c==']' && s.peek()=='[') {
							s.pop();
						}else if(c=='>' && s.peek()=='<') {
							s.pop();
						}else if(s.isEmpty()){
							res=0;
							break f;
						}else {
							res=0;
							break f;
						}
					}
				}
			}
			if(!s.isEmpty()) {
				res=0;
			}
			bw.write("#"+t+" "+res+"\n");
		}
		bw.flush();
	}
}
