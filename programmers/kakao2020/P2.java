package programmers.kakao2020;

import java.util.Stack;

public class P2 {

	public static void main(String[] args) {
		String s = "()))((()";
		System.out.println(solution(s));
	}
	
	static String solution(String s) {
		StringBuilder sb = new StringBuilder();
		
		// s가 빈 문자열이면 반환
		if(s.length()==0) return "";
		
		// s가 이미 올바른 문자열이라면
		if(isBalance(s)) return s;
		
		// 1단계 : 문자열 s를 u와 v로 분리
		// u는 최소의 균형잡힌 문자열
		int cut_index=0;
		int balance=0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='(') {
				balance++;
			}else balance--;
			
			if(balance==0) {
				cut_index=i;
				break;
			}
		}
		String u = s.substring(0,cut_index+1);
		String v = s.substring(cut_index+1);
		
		
		// 2단계 : u가 올바른 문자열인지 확인
		// 1) u가 올바른 문자열이면,
		if(isBalance(u)) return sb.append(u+solution(v)).toString();
		// 2) u가 올바른 문자열이 아니라면
		else {
			StringBuilder tempS = new StringBuilder();
			// 1. 빈 문자열에 '(' 붙이기
			tempS.append('(');
			// 2. 문자열 v에 대해 1단계부터 돌린 결과 붙이기
			tempS.append(solution(v));
			// 3. ')'붙이기
			tempS.append(')');
			// 4. u의 첫번째와 마지막 문자열 제거 후, 나머지 괄호 뒤집어서 뒤에 붙이기
			String u_cut = u.substring(1,u.length()-1);
			for(int i=0; i<u_cut.length(); i++) {
				if(u_cut.charAt(i)=='(') tempS.append(')');
				else tempS.append('(');
			}
			// 5. 생성된 문자열(tempS)반환
			return tempS.toString();
		}
	}
	
	static boolean isBalance(String s) {
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='(') {
				stack.add(s.charAt(i));
			}else {
				if(stack.isEmpty()) return false;
				else stack.pop();
			}
		}
		
		return true;
	}
}