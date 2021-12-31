package boj.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_6236 {
    static int N,M;
    static int[] cost;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cost = new int[N];
        int startK = 0;
        int sum = 0;
        for(int i=0; i<N; i++){
            cost[i] = Integer.parseInt(br.readLine());
            startK = Math.max(startK, cost[i]);
            sum += cost[i];
        }

        System.out.println(binarySearch(startK,sum));

    }

    static public int binarySearch(int startK, int sum) {
        int start = startK;
        int end = sum;
        int mid = 0;

        while(start<=end){
            mid = (start+end)/2;
            int tempM = withdraw(mid);

            if(tempM<=M) end = mid-1;
            else start = mid+1;
        }

        return mid;
    }

    static public int withdraw(int K) {
        int money = 0;
        int tempM = 0;

        for(int i=0; i<N; i++){
            if(money<cost[i]){
                tempM++;
                money = K-cost[i];
            }
            else {
                money -= cost[i];
            }
        }

        return tempM;
    }
}
