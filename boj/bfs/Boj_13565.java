package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_13565 {
    static int N,M;
    static int[][] map;
    static int inner_cnt=0;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Pos{
        int r;
        int c;
        public Pos(int r, int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        Queue<Pos> q = new LinkedList<>();
        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = s.charAt(j)-'0';
                if(i==0 && map[i][j]==0) {
                    q.offer(new Pos(i,j));
                    map[i][j]=1;
                }
                else if(i==N-1 && map[i][j]==0) inner_cnt++;
            }
        }

        bfs(q);

        int temp_cnt = 0;
        for(int i=0; i<M; i++){
            if(map[N-1][i]==0) temp_cnt++;
        }
        if(temp_cnt==inner_cnt) System.out.println("NO");
        else System.out.println("YES");
    }

    public static void bfs(Queue<Pos> q){

        while(!q.isEmpty()){
            Pos cur = q.poll();
            int r = cur.r;
            int c = cur.c;

            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if(map[nr][nc]==0){
                        q.offer(new Pos(nr,nc));
                        map[nr][nc]=1;
                    }
                }
            }
        }
    }
}
