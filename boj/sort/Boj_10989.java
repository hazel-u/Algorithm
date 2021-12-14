package boj.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_10989 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] cnt = new int[10001];

        for(int i=0; i<N; i++){
            cnt[Integer.parseInt(br.readLine())]++;
        }
        for(int i=0; i<10001; i++){
            for(int j=0; j<cnt[i]; j++){
                bw.write(i+"\n");
            }
        }

        bw.flush();
    }
}
