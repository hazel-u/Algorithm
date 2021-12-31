package boj.문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_14405 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<s.length(); i++){
            char temp = s.charAt(i);
            sb.append(temp);
            if(temp=='a' || temp=='e' || temp=='i' || temp=='o' || temp=='u'){
                if(sb.toString().equals("pi") || sb.toString().equals("ka") || sb.toString().equals("chu")){
                    sb = new StringBuilder();
                }else {
                    System.out.println("NO");
                    System.exit(0);
                }
            }
        }
        if(sb.toString().length()>0) System.out.println("NO");
        else System.out.println("YES");
    }
}
