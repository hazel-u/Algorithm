package boj.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_18310 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int n=0; n<N; n++){
            arr[n] = Integer.parseInt(st.nextToken());
        }
        // 정렬
        Arrays.sort(arr);

        int middle = (N-1)/2;

        System.out.println(arr[middle]);

    }
}
