package test.kakao2022;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P3 {

	public static void main(String[] args) {
		int[] fees = {1, 461, 1, 10};
		String[] records = {"00:00 1234 IN"};
		
		solution(fees, records);
	}
	
	public static int[] solution(int[] fees, String[] records) {
        
        int defaultTime = fees[0];
        int defaultCost = fees[1];
        int addTime = fees[2];
        int addCost = fees[3];
        
        HashMap<String, Integer> label = new HashMap<>();
        int[][] info = new int[1000][5]; // 차량번호, 들어온 시간, 총시간, way(in -1, out 1), 가격
        int index = 0;
        
        for(int i=0; i<records.length; i++) {
        	StringTokenizer st = new StringTokenizer(records[i]);
        	
        	String[] original_time = st.nextToken().split(":");
        	int time = Integer.parseInt(original_time[0])*60 + Integer.parseInt(original_time[1]);
        	String carNo = st.nextToken();
        	String way = st.nextToken();
        	
        	if(way.equals("IN")) {
        		if(!label.containsKey(carNo)) {
	        		label.put(carNo, index);
	        		info[index][0]=Integer.parseInt(carNo);
	        		info[index][1]=time;
	        		info[index][2]=0;
	        		info[index][3]=-1; // in
	        		index++;
        		}else {
            		int temp_index = label.get(carNo);
	        		info[temp_index][1]=time;
	        		info[temp_index][3]=-1; // in
        		}
        	}
        	else if(way.equals("OUT")) {
        		int temp_index = label.get(carNo);
        		int total_time = time - info[temp_index][1];
        		info[temp_index][2]+=total_time;
        		info[temp_index][3]=1; // out
        	}
        }
        
        // 요금계산 & 적당한 크기의 배열로 옮기기
        int[][] total_info = new int[index][2]; // 차량번호, 가격
        for(int i=0; i<index; i++) {
        	if(info[i][3]==-1) { // 출차 x
        		info[i][2] += (23*60+59)-info[i][1];
        	}
        	
        	if(info[i][2]>0 && info[i][2]<=defaultTime) {
        		info[i][4]=defaultCost;
        	}else if(info[i][2]>defaultTime){
        		info[i][4] = defaultCost + (int)Math.ceil((double)(info[i][2]-defaultTime)/addTime)*addCost;
        	}
        	
        	total_info[i][0]=info[i][0];
        	total_info[i][1]=info[i][4];
        }
        
        Arrays.sort(total_info, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
        
        int[] answer = new int[index];
        for(int i=0; i<index; i++) {
        	answer[i]=total_info[i][1];
        	System.out.println(total_info[i][1]);
        }
        
        return answer;
    }
}
