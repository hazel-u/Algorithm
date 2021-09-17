package swea.LIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea_3307_NlogN {
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N];
			dp = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int n=0; n<N; n++) {
				arr[n]=Integer.parseInt(st.nextToken());
			}
			
			int index = 0;
			
			for(int n=0; n<N; n++) {
				if(index==0) dp[index++]=arr[n];
				else {
					if(dp[index-1]<arr[n]) dp[index++]=arr[n];
					else {
						int temp_idx = find(0,index, arr[n]);
						dp[temp_idx]=arr[n];
					}
				}
			}
			bw.write("#"+t+" "+(index)+"\n");
		}
		bw.flush();
	}

	static int find(int start, int end, int value) {
		int middle = end;
		
		while(start<=end) {
			middle=(start+end)/2;
			
			if(dp[middle]<value) start = middle+1;
			else if(dp[middle]>value) end = middle-1;
			else return middle;
		}
		return start;
	}
}
