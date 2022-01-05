package boj.math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_22864 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 한시간 일 할 때마다 쌓이는 피로도의 양
        int A = Integer.parseInt(st.nextToken());
        // 한시간동안 처리할 수 있는 일의 양
        int B = Integer.parseInt(st.nextToken());
        // 한시간 쉬면 줄어드는 피로도의 양
        int C = Integer.parseInt(st.nextToken());
        // 하루 최대 피로도의 양
        int M = Integer.parseInt(st.nextToken());

        // 피로도
        int tired = 0;
        // 일
        int work = 0;

        for(int i=0; i<24; i++){
            if(tired+A<=M){
                tired+=A;
                work+=B;
            }
            else {
                tired-=C;
                if(tired<0) tired=0;
            }
        }

        System.out.println(work);
    }
}
