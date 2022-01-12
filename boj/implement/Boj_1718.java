package boj.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_1718 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray(); // 평문
        char[] b = br.readLine().toCharArray(); // 암호화문

        for(int i=0; i<a.length; i++){
            if(a[i]==' ') {
                System.out.print(' ');
                continue;
            }
            int v = a[i]-b[i%b.length];
            if(v<=0) System.out.print((char) (v+25+'a'));
            else System.out.print((char) (v-1+'a'));
        }
    }
}
