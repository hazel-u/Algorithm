package boj.누적합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_19951 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 연병장 크기
        int M = Integer.parseInt(st.nextToken()); // 조교 수

        int[] sand = new int[N];
        int[] action = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            sand[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(k>=0) { // 흙 덮기

            }
            else { // 흙 파기

            }
        }
    }
}
