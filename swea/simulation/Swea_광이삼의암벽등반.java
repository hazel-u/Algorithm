package swea.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_광이삼의암벽등반 {

    static int R, C, L;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static int[][] map;
    static boolean[][][] visited;
    static class Pos{
        int r;
        int c;
        int gr;
        int dist;
        Pos(int r, int c, int gr, int dist){
            this.r=r;
            this.c=c;
            this.gr=gr;
            this.dist=dist;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[R][C];
            visited = new boolean[R][C][10];

            int startR = 0;
            int startC = 0;

            for(int r=0; r<R; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<C; c++){
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if(map[r][c]==2){
                        startR=r;
                        startC=c;
                    }
                }
            }

            bw.write("#"+tc+" "+solution(startR, startC)+"\n");
        }
        bw.flush();
    }

    public static int solution(int startR, int startC){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(startR, startC, 0,0));
        visited[startR][startC][0]=true;

        while(!q.isEmpty()){
            Pos cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int gr = cur.gr;
            int dist = cur.dist;

            if(map[r][c]==3){
                return gr;
            }
            else if(dist==L) continue;

            if(gr>=9) return -1;

            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr>=0 && nr<R && nc>=0 && nc<C){
                    if(!visited[nr][nc][gr]){
                        q.offer(new Pos(nr, nc, gr, dist + 1));
                        visited[nr][nc][gr] = true;
                        if(map[r][c]==1){
                            // 새로운 고리로 바꾸고 가는 경우
                            q.offer(new Pos(nr, nc,gr+1,1));
                            visited[nr][nc][gr+1]=true;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
