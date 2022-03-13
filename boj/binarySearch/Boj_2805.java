package boj.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2805 {
    static int N;
    static long M;
    static int cutLen = 0;
    static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            tree[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tree);
        
        binarySearch(0, tree[N-1]+1);

        System.out.println(cutLen);
    }
    
    public static void binarySearch(int start, int end) {

        while(start<=end) {
            int middle = (start+end)/2;

            long tempGetTree = cutTree(middle);
            if(tempGetTree>=M) {
                start = middle+1;
                cutLen=middle;
            }
            else {
                end = middle-1;
            }
        }
        return;
    }

    public static long cutTree(int middle) {
        long cnt = 0;

        for(int i=N-1; i>=0; i--) {
            if(tree[i]<=middle) break;
            cnt += tree[i]-middle;
        }

        return cnt;
    }
}
