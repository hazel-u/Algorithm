package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_2670 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        double[] arr = new double[N];

        for(int i=0; i<N; i++){
            arr[i] = Double.parseDouble(br.readLine());
        }

        double max = 0.0f;

        // 시작할 index
        for(int start = 0; start<N; start++){
            double dp = 1;
            // 1개부터 N-start개 까지 곱해보며 최댓값 찾기
            for(int i=0; i<N-start; i++){
                dp *= arr[start+i];
                max = Math.max(max, dp);
            }
        }

        System.out.printf("%.3f", max);
    }
}
