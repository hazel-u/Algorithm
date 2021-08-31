package programmers.kakao2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class P2 {

	
	public static void main(String[] args) {
//		String[] orders= {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//		int[] course = {2,3,4};
		
		String[] orders= {"XYZ", "XWY", "WXA"};
		int[] course = {2,3,4};
		
		System.out.println(solution(orders, course));
	}
	
	static LinkedList<Character> res;
	static List<String> combi;

	public static String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		combi = new LinkedList<>();
		
		for(int i=0; i<orders.length; i++) {
        	String[] one = orders[i].split("");
        	Arrays.sort(one);
        	
        	for(int j=0; j< one.length-1; j++) {
        		for(int k=0; k< course.length; k++) {
        			dfs(one, j, 1, course[k], one[j]);
        		}
        	}
        }
		
		Map<String, Integer> map = new HashMap<>();
		for(String menu:combi) {
			map.put(menu, map.getOrDefault(menu, 0)+1);
		}
		
		List<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return map.get(o2)-map.get(o1);
			}
		});
		
		List<String> result = new ArrayList<>();
		for(int i=0; i<course.length; i++) {
			int max=0;
			
			for(String courseMenu:list) {
				if(map.get(courseMenu)>1 && courseMenu.length() == course[i]) {
					if(map.get(courseMenu) >= max) {
						result.add(courseMenu);
						max = map.get(courseMenu);
					}
				}
			}
		}
		Collections.sort(result);
		
		answer = new String[result.size()];
		result.toArray(answer);
		
        return answer;
	}

	static void dfs(String[] one, int idx, int len, int courseLen, String str) {
		if(len == courseLen) {
			combi.add(str);
		}
		
		for(int i= idx+1; i<one.length; i++) {
			dfs(one, i, len+1, courseLen, str+one[i]);
		}
	}
	
}
