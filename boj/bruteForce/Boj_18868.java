package boj.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_18868 {
    static int N,M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] planet = new int[M][N];

        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            for(int n=0; n<N; n++){
                planet[m][n] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        for(int i=0; i<M; i++){
            for(int j=i+1; j<M; j++){
                boolean p = true;
                f: for(int a=0; a<N; a++){
                    for(int b=a+1; b<N; b++){
                        if(planet[i][a]<planet[i][b] && planet[j][a]<planet[j][b]){
                            continue;
                        }
                        else if(planet[i][a]>planet[i][b] && planet[j][a]>planet[j][b]){
                            continue;
                        }
                        else if(planet[i][a]==planet[i][b] && planet[j][a]==planet[j][b]){
                            continue;
                        }
                        else{
                            p = false;
                            break f;
                        }
                    }
                }
                if(p) result++;
            }
        }
        System.out.println(result);
    }
}
