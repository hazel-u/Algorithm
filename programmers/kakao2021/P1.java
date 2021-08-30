package programmers.kakao2021;

import java.util.LinkedList;

public class P1 {

	public static void main(String[] args) {
		String new_id = "...!@BaT#*..y.abcdefghijklm";
//		String new_id = "z-+.^.";
//		String new_id = "=.=";
//		String new_id = "123_.def";
//		String new_id = "abcdefghijklmn.p";
		
		String id = solution(new_id);

		System.out.println(id);
	}
	
	public static String solution(String new_id) {
		StringBuilder id = new StringBuilder();
		int len = new_id.length();
		String s = "-_.";
		
		new_id = new_id.toLowerCase(); // 1번
		
		LinkedList<Character> id_list = new LinkedList<>();
		
		char[] new_id_char = new_id.toCharArray();
		for(int i=0; i<len; i++) {
			// 2번 규칙에 맞는 문자 확인, 규칙에 안맞으면 제거
			if(s.contains(Character.toString(new_id_char[i])) || Character.isDigit(new_id_char[i]) || Character.isLowerCase(new_id_char[i])) {
				continue;
			}else new_id_char[i]=' ';
		}
		
		
		int flag=0;
		for(int i=0; i<len; i++) {
			char temp = new_id_char[i];
			if(temp==' ') continue;
			else if(temp=='.') {
				flag++;
			}else {
				if(flag>=1) { // 3번 연속되는 마침표 제거
					id_list.add('.');
					flag=0;
				}
				id_list.add(temp);
			}
		}
		if(flag>=1) id_list.add('.');
		
		// 4번 마침표가 처음이나 끝이면 제거
		if(id_list.get(0)=='.') id_list.remove(0);
		if(id_list.size()>0 && id_list.get(id_list.size()-1)=='.') id_list.remove(id_list.size()-1);
		
		len = id_list.size();
		
		// 5번 빈 문자열이면 a대입
		if(len==0) id_list.add('a'); 
		
		len=id_list.size();
		if(len>=16) { // 6번 아이디가 16글자 이상일 때, 첫 15문자를 제외한 나머지 문자 제거
			for(int i=0; i<15; i++) {
				id.append(id_list.get(i));
			}
			if(id_list.get(14)=='.') id.delete(14, 15);
		}else if(len<=2) { // 7번 아이디가 2글자 이하일 때 아이디의 마지막 문자를 길이가 3이 될때 까지 붙이기 
			char last = id_list.get(len-1);
			for(int i=len; i<3; i++) {
				id_list.add(last);
			}
			
			for(int i=0; i<3; i++) {
				id.append(id_list.get(i));
			}
		}else {
			for(int i=0; i<len; i++) {
				id.append(id_list.get(i));
			}
		}
		
		return id.toString();
	}

}
