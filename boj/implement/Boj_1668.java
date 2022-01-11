package boj.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_1668 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;
        int leftCnt = 0;
        int rightCnt = 0;
        for(int i=0; i<N; i++){
            if(max<arr[i]){
                max=arr[i];
                leftCnt++;
            }
        }

        max=0;
        for(int i=N-1; i>=0; i--){
            if(max<arr[i]){
                max=arr[i];
                rightCnt++;
            }
        }

        System.out.println(leftCnt);
        System.out.println(rightCnt);
    }
}
