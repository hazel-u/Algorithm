package boj.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수찾기_유혜승 {
    static int N,M;
    static int[] A;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int x = Integer.parseInt(st.nextToken());
            if(x>A[N-1] || x<A[0]) System.out.println(0);
            else System.out.println(binarySearch(x));
        }
    }

    static public int binarySearch(int x) {
        int start = 0;
        int end = N-1;

        while(start<=end){
            int middle = (start+end)/2;

            if(A[middle]==x) return 1;
            else if(A[middle]<x) {
                start=middle+1;
            }
            else end = middle-1;
        }

        return 0;
    }
}
