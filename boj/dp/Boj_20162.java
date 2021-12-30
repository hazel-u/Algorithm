package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_20162 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N];
        int[] eat = new int[N];

        for(int i=0; i<N; i++){
            eat[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = eat[0];

        for(int i=1; i<N; i++){
            dp[i] = eat[i];

            for(int j=0; j<i; j++){
                if(eat[j]<eat[i]){
                    dp[i] = Math.max(dp[i], dp[j]+eat[i]);
                }
            }
        }

        int result = 0;
        for(int i=0; i<N; i++){
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
