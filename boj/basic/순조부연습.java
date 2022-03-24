package boj.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 순조부연습 {
    // 순열 (순서를 고려해 나열하는 경우의 수)
    static int N,R; // N 입력받을 데이터 수, R 선택할 데이터 수
    static int[] input, res;
    static boolean[] visited;

    static class test implements Comparable<test> {
        int r;
        int c;

        public int compareTo(test t) {
            return this.r - t.r;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

        input = new int[N];
        res = new int[R];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        permutation(0);
    }

    public static void permutation(int cnt) {
        if(cnt==R) {
            System.out.println(Arrays.toString(res));
            return;
        }

        for(int i=0; i<N; i++) {
            if(visited[i]) continue;

            res[cnt] = input[i];
            visited[i]=true;
            permutation(cnt+1);
            visited[i]=false;
        }
    }

    public static void combination(int cnt, int start) {
        if(cnt==R) {
            System.out.println(Arrays.toString(res));
            return;
        }

        for(int i=start; i<N; i++) {
            res[cnt]=input[i];
            combination(cnt+1, i+1);
        }
    }

    public static void subset(int cnt) {
        if(cnt==N) {
            for(int i=0; i<N; i++) {
                if(visited[i]) System.out.print(input[i]+" ");
            }
            System.out.println();
            return;
        }

        visited[cnt]=true;
        subset(cnt+1);
        visited[cnt]=false;
        subset(cnt+1);
    }
}
