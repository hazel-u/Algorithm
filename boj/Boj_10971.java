package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10971 {

    static int N;
    static int[][] w;
    static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        // 못가거나 제자리인 경우 0
        w = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                w[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[N];

        for(int n=0; n<N; n++){
            go(0,n,n,0, visited);
        }

        System.out.println(minCost);
    }

    public static void go(int cnt, int start, int now, int cost, boolean[] visited){
        // 기저조건
        if(cnt>0 && start==now){
            if(cnt==N){
                minCost = Math.min(minCost, cost);
            }
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i] && w[now][i]>0){
                visited[i]=true;
                go(cnt+1, start, i, cost+w[now][i], visited);
                visited[i]=false;
            }
        }

    }
}
