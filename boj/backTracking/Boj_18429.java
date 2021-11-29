package boj.backTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_18429 {

    static int N, K;
    static boolean[] visited;
    static int[] weight;
    static int res = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weight = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // start back tracking
        for(int i=0; i<N; i++){
            visited[i]=true;
            // 가능한지 확인
            if(weight[i]-K>=0){
                work(0, 500+(weight[i]-K));
            }
            visited[i]=false;
        }

        System.out.println(res);
    }

    public static void work(int cnt, int w){
        if(cnt==N-1){
            res++;
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                if((w-K+weight[i])>=500){
                    visited[i]=true;
                    work(cnt+1, w-K+weight[i]);
                    visited[i]=false;
                }
            }
        }
    }
}
