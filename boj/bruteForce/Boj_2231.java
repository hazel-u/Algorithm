package boj.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_2231 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int tempN = N;
        int cnt = 0; // 자리 수 세기
        while(tempN!=0){
            tempN/=10;
            cnt++;
        }
        int tempM = (int) ((cnt-1)*9 + N/Math.pow(10,cnt-1)) -1;

        for(int i=tempM; i>=0; i--){
            int temp = N-i;
            if(sum(temp)==i){
                System.out.println(temp);
                System.exit(0);
            }
        }

        System.out.println(0);
    }

    public static int sum(int temp){
        int result=0;

        while(temp!=0){
            result+=temp%10;
            temp/=10;
        }

        return result;
    }
}
