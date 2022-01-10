package boj.backTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_6603 {

    static int N;
    static int[] arr;
    static boolean[] visited;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            if(N==0) break;

            arr = new int[N];
            visited = new boolean[N];

            for(int n=0; n<N; n++){
                arr[n] = Integer.parseInt(st.nextToken());
            }

            back(0,0);
            bw.write("\n");
        }

        bw.flush();
    }

    public static void back(int cnt, int start) throws Exception{
        if(cnt==6){
            for(int i=0; i<N; i++){
                if(visited[i]) bw.write(arr[i]+" ");
            }
            bw.write("\n");
            return;
        }

        for(int i=start; i<N; i++){
            if(visited[i]) continue;

            visited[i]=true;
            back(cnt+1, i+1);
            visited[i]=false;
        }
    }
}
