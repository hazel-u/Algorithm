package boj.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_20154 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] alpha = {3,2,1,2,3,3,3,3,1,1,3,1,3,3,1,2,2,2,1,2,1,1,2,2,2,1};

        String str = br.readLine();

        int result = 0;
        for(int i=0; i<str.length(); i++){
            result += alpha[str.charAt(i)-'A'];
        }

        if(result%2==1) System.out.println("I'm a winner!");
        else System.out.println("You're the winner?");
    }
}
