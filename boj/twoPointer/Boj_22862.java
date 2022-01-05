package boj.twoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_22862 {
    static int N,K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findLongLength());
    }

    public static int findLongLength(){
        int result = 0;

        int end=-1;
        int count=0;

        for(int start=0; start<N; start++){
            if(start>0 && arr[start-1]%2!=0) count--;

            while(end+1<N && count<=K){
                if(arr[++end]%2!=0) count++;
            }
            result = Math.max(result, end-start+1-count);
        }

        return result;
    }
}
