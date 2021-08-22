package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16953 {
	static int res=987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		Long A = Long.parseLong(st.nextToken());
		Long B = Long.parseLong(st.nextToken());
		
		find(A, B, 1);
		
		if(res==987654321) {
			System.out.println(-1);
		}else {
			System.out.println(res);
		}
	}
	
	private static void find(long start, long end, int cnt) {
		if(start==end) {
			res = Math.min(res, cnt);
			return;
		}else if(start>end) {
			return;
		}else {
			find(start*2, end, cnt+1);
			find(start*10+1,end, cnt+1);
		}
	}
}
