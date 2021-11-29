package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1613 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] conn = new boolean[N][N];
        for(int n=0; n<N; n++) conn[n][n]=true;

        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            conn[a][b]=true;
        }

        for(int k=0; k<N; k++){ // 경유지
            for(int i=0; i<N; i++){ // 출발지
                for(int j=0; j<N; j++){ // 도착지
                    conn[i][j]=conn[i][j] || (conn[i][k]&&conn[k][j]);
                }
            }
        }

        int S = Integer.parseInt(br.readLine());
        for(int s=0; s<S; s++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            if(conn[a][b]) bw.write(-1+"\n");
            else if(conn[b][a]) bw.write(1+"\n");
            else bw.write(0+"\n");
        }

        bw.flush();
    }
}
