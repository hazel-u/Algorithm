package swea.permutation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_6808 {
	static int[] i_card;
	static int[] k_card;
	static int[] p_card; // 인영이 카드(permu)
	static boolean[] isSelected;
	static int win;
	static int lose;
	
	public static void permutation(int cnt) {
		if(cnt==9) {
			int i_score=0;
			int k_score=0;
			for(int i=0; i<9; i++) {
				if(p_card[i]<k_card[i]) k_score+=(p_card[i]+k_card[i]);
				else i_score+=(p_card[i]+k_card[i]);
			}
			
			if(k_score>i_score) win++;
			else if(k_score<i_score) lose++;
			
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(isSelected[i]) continue;
			
			p_card[cnt]=i_card[i];
			isSelected[i]=true;
			permutation(cnt+1);
			isSelected[i]=false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		
		for(int t=1; t<=T; t++) {
			win=0;
			lose=0;
			st = new StringTokenizer(br.readLine());
			boolean[] cards = new boolean[19];
			
			while(st.hasMoreTokens()) {
				cards[Integer.parseInt(st.nextToken())]=true; // 규영이의 카드 t, 인영이의 카드 f
			}
			
			i_card=new int[9];
			k_card=new int[9];
			p_card=new int[9];
			isSelected= new boolean[9];
			
			int i_cnt=0;
			int k_cnt=0;
			
			for(int i=1; i<19; i++) {
				if(cards[i]) {
					k_card[k_cnt++]=i;
				}else {
					i_card[i_cnt++]=i;
				}
			}
			
			permutation(0);
			
			bw.write("#"+t+" "+win+" "+lose+"\n");
		}
		bw.flush();
	}
}
