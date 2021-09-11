package test.kakao2022;

import java.util.Arrays;
import java.util.LinkedList;

public class P4 {

	public static void main(String[] args) {
		int n=10;
		int[] info = {5,5,0,0,0,0,0,0,0,0,0};
		
		solution(n, info);
	}
	public static int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        int[] ryan = new int[11];
        
        
        for(int i=0; i<11; i++) { // 해당 과녁에서 어피치를 이기기 위한 필요한 최소 화살 개수
        	ryan[i] = info[i]+1;
        }
        int res_total = 0;
        int temp_total = 1;
        LinkedList<Integer> ryan_choice = null;
        boolean[] check = new boolean[11];
        
        while(true) {
        	temp_total=1;
        	ryan_choice = new LinkedList<>();
        	
        	int maxNeedCnt=0;
        	int maxNeedIndex=-1;
        	
        	int arrow=0;
        	int index=0;
        	while(arrow<n) {
        		if(index>10) break;
        		if(!check[index] && arrow + ryan[index]<=n) {
        			arrow+=ryan[index];
        			if(maxNeedCnt<ryan[index]){
        				maxNeedCnt=ryan[index];
        				maxNeedIndex = index;
        			}
        			temp_total += (10-index);
        			ryan_choice.add(10-index);
        		}
        		index++;
        	}
        	if(res_total>temp_total) {
        		break;
        	}
        	res_total=temp_total;
        	check[maxNeedIndex]=true;
        	
        	answer = new int[11];
        	for(int i=0; i<ryan_choice.size(); i++) {
        		answer[10-ryan_choice.get(i)]=ryan[10-ryan_choice.get(i)];
        	}
        	if(arrow<n) {
        		int remain = n-arrow;
        		answer[10]+=remain;
        	}
        }
        int apeach_score = 0;
        for(int i=0; i<11; i++) {
        	if(info[i]!=0 && answer[i]==0) apeach_score+=(10-i);
        }
        
        int ryan_score = 0;
        for(int i=0; i<11; i++) {
        	if(answer[i]!=0) ryan_score+=(10-i);
        }
        System.out.println(ryan_score+", "+apeach_score);
        if(ryan_score<=apeach_score) {
        	int[] lose = new int[1];
        	lose[0]=-1;
        	return lose;
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
