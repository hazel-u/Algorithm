package test.kakao2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P1 {

	public static void main(String[] args) {
		String[] id_list = {"con", "ryan"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
		int k = 2;

		solution(id_list, report, 2);
		
	}
	
	public static int[] solution(String[] id_list, String[] report, int k) {
		int id_cnt= id_list.length;
		int[] answer = new int[id_cnt];
		
		HashMap<String, Integer> label = new HashMap<>();
		for(int i=0; i<id_cnt; i++) {
			label.put(id_list[i], i);
		}
		
		int[] reported = new int[id_cnt]; // 신고당한 횟수 카운트
		ArrayList<String>[] reporting = new ArrayList[id_cnt];// 내가 신고한 애 저장
		for(int i=0; i<id_cnt; i++) {
			reporting[i]=new ArrayList<>();
		}
		
		for(int i=0; i<report.length; i++) {
			StringTokenizer st = new StringTokenizer(report[i]);
			String p1 = st.nextToken();
			String p2 = st.nextToken();
			
			// 처음 신고하는 애면
			if(!reporting[label.get(p1)].contains(p2)) {
				reported[label.get(p2)]++;
				reporting[label.get(p1)].add(p2);
			}
		}
		
		for(int i=0; i<id_cnt; i++) {
			for(int j=0; j<reporting[i].size(); j++) {
				String search = reporting[i].get(j);
				if(reported[label.get(search)]>=k) {
					answer[i]++;
				}
			}
		}
		
		return answer;
	}

}
