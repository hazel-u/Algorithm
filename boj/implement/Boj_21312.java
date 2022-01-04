package boj.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_21312 {
    public static int compare(int a, int b){
        if(a%2==1) {
            if(b%2==1) {
                return Math.max(a,b);
            }
            else return a;
        }
        else if(b%2==1) {
            return b;
        }
        else return Math.max(a,b);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int result = compare(A, compare(B,C));

        result = compare(result, A*B);
        result = compare(result, B*C);
        result = compare(result, A*C);
        result = compare(result, A*B*C);

        System.out.println(result);

    }
}
