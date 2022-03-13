package boj.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2512 {

    static int up = 0;
    static int[] cities;
    static int N;
    static int M; // 총 예산
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        cities = new int[N];
        int sum = 0;
        int maxV = 0;
        for(int i=0; i<N; i++){
            cities[i] = Integer.parseInt(st.nextToken());
            sum += cities[i];
            maxV = Math.max(maxV, cities[i]);
        }
        M = Integer.parseInt(br.readLine());

        // 전체 예산으로 커버 가능한지 체크
        if(sum<=M) {
            System.out.println(maxV);
            System.exit(0);
        }

        binarySearch(0,100000);

        maxV = 0;
        for(int i=0; i<N; i++){
            maxV = Math.max(maxV, cities[i]>up?up:cities[i]);
        }
        System.out.println(maxV);
    }

    static public void binarySearch(int start, int end) {

        while(start<=end) {
            int middle = (start+end)/2;

            int sumAllCites = sumFunc(middle);
            if(sumAllCites>M) {
                end = middle-1;
            }
            else {
                up = middle;
                start = middle+1;
            }
        }
    }

    static public int sumFunc(int middle) {
        int sum = 0;
        for(int i=0; i<N; i++){
            sum += cities[i]>middle?middle:cities[i];
        }
        return sum;
    }
}
