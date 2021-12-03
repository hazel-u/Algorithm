package swea.permutation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea_1865 {

    static double max;
    static int N;
    static double[][] per;
    static boolean[] visited;
    static int[] resArr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            per = new double[N][N];
            visited = new boolean[N];
            max=0;

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    per[i][j]=Double.parseDouble(st.nextToken())/100;
                }
            }

            permutation(0, 1);
            max*=100;
            bw.write("#"+t+" "+String.format("%.6f",max)+"\n");
        }
        bw.flush();
    }

    public static void permutation(int cnt, double res){
        if(res<=max){
            return;
        }
        if(cnt==N){
            max = Math.max(max,res);
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]) {
                visited[i] = true;
                permutation(cnt + 1, res * per[cnt][i]);
                visited[i] = false;
            }
        }
    }
}
