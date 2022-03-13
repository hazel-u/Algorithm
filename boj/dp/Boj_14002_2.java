package boj.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_14002_2 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = new int[N];
        int[] dp = new int[N];
        Arrays.fill(dp,1);

        int cnt = 0;
        while(st.hasMoreTokens()) {
            A[cnt++] = Integer.parseInt(st.nextToken());
        }
        int result=1;
        for(int i=0; i<N; i++){
            for(int j=0; j<i; j++){
                if(A[i]>A[j] && dp[i]<dp[j]+1) {
                    dp[i] = dp[j]+1;
                    result = Math.max(result, dp[i]);
                }
            }
        }
        bw.write(result+"\n");

        Stack<Integer> stack = new Stack<>();
        for(int i=N-1; i>=0; i--) {
            if(dp[i]==result) {
                stack.push(A[i]);
                result--;
            }
        }

        while(!stack.isEmpty()) {
            bw.write(stack.pop()+" ");
        }

        bw.flush();
    }
}
