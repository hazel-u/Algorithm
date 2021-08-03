package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_14002 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] A = new int[N];
		int[] dp = new int[N];
		int result=1;
		Arrays.fill(dp, 1);
		
		int in=0;
		while(st.hasMoreTokens()) {
			A[in++]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(A[i]>A[j] && dp[i]<dp[j]+1) {
					dp[i]=dp[j]+1;
					result = Math.max(dp[i], result);
				}
			}
		}
		System.out.println(result);
		
		int cnt=result;
		Stack<Integer> stack = new Stack<>();
		for(int i=N-1; i>=0; i--) {
			if(cnt==dp[i]) {
				stack.push(A[i]);
				cnt--;
			}
		}
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
	}
}
