package swea.구간합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_5604 {

	static long[] dp;
	static long start, end;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long start = Long.parseLong(st.nextToken());
			long end = Long.parseLong(st.nextToken());
			long result=0;
			dp = new long[10]; // 0 ~ 9까지의 개수 저장
			long point=1;
			
			while(start<=end) {
				while(end%10!=9 && start<=end) {
					solve(end, point);
					end--;
				}
				if(start>end) break;
				
				while(start%10!=0 && start<=end) {
					solve(start, point);
					start++;
				}
				start/=10;
				end/=10;
				
				for(int i=0; i<10; i++) {
					dp[i] += (end-start+1) * point;
				}
				point*=10;
			}
			
			for(int i=1; i<10; i++) {
				result+=(dp[i]*i);
			}
			
			bw.write("#"+t+" "+result+"\n");
		}
		bw.flush();
	}
	
	public static void solve(long end, long point) {
		while(end>0) {
			String s = String.valueOf(end);
			int endNum = s.charAt(s.length()-1)-'0';
			dp[endNum]+=point;
			end/=10;
		}
	}
}
