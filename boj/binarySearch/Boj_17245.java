package boj.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17245 {

    static int N;
    static int[][] map;
    static long h;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int maxV = 0;
        long sum = 0;

        map = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                maxV = Math.max(maxV, map[i][j]);
                sum += map[i][j];
            }
        }

        binarySearchTree(0, maxV, sum);

        System.out.println(h);
    }

    static void binarySearchTree(int start, int end, long sum) {

        while(start<=end) {
            int middle = (start+end)/2;

            long tempSum = check(middle);
            if((double)tempSum/sum<0.5) {
                start = middle+1;
            }
            else {
                h=middle;
                end = middle-1;
            }
        }
    }

    static long check(int middle) {
        long cover = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j]>=middle) {
                    cover += middle;
                }
                else {
                    cover += map[i][j];
                }
            }
        }

        return cover;
    }
}
