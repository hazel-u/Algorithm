package boj.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2961 {
	static int[][] foods;

	static int s; // 신맛
	static int b; // 쓴맛
	static int N;
	static boolean[] isSelected;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		foods=new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			foods[i][0]=Integer.parseInt(st.nextToken()); // 신맛
			foods[i][1]=Integer.parseInt(st.nextToken()); // 쓴맛
		}

		isSelected = new boolean[N];
		min=987654231;
		subset(0);
		
		System.out.println(min);
		
	}
	
	public static void subset(int cnt) {
		if(cnt==N) {
			s=1;
			b=0;
			boolean flag=false;
			for(int i=0; i<N; i++) {
				if(isSelected[i]) { // 선택된것
					s*=foods[i][0];
					b+=foods[i][1];
					flag=true;
				}
			}
			if(flag)min = Math.min(min, Math.abs(s-b));
			return;
		}
		isSelected[cnt]=true;
		subset(cnt+1);
		isSelected[cnt]=false;
		subset(cnt+1);
	}
}
