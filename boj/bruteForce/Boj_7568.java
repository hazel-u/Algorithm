package boj.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_7568 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] weight = new int[N];
        int[] height = new int[N];

        int[] count = new int[N];
        Arrays.fill(count,1);

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            height[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i==j) continue;

                if(weight[i]<weight[j] && height[i]<height[j]) count[i]++;
            }
        }

        for(int i=0; i<N; i++){
            System.out.print(count[i]+" ");
        }
    }
}
