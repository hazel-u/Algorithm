package boj.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_14716 {

    static int[] dr = {-1,-1,0,1,1,1,0,-1};
    static int[] dc = {0,1,1,1,0,-1,-1,-1};

    static int[][] map;

    static int R,C;

    static class Pos{
        int r;
        int c;
        Pos(int r, int c) {
           this.r = r;
           this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        int cnt=2;

        ArrayList<Pos> list = new ArrayList<>();

        for(int r=0; r<R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c]==1) {
                    list.add(new Pos(r,c));
                }
            }
        }
        // input end

        for(Pos p : list) {
            if(map[p.r][p.c]==1) {
                bfs(p.r, p.c, cnt);
                cnt++;
            }
        }

        System.out.println(cnt-2);
    }

    public static void bfs(int r, int c, int cnt) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(r,c));
        map[r][c]=cnt;

        while(!q.isEmpty()) {
            Pos cur = q.poll();

            for(int d=0; d<8; d++) {
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];
                if(inrange(nr,nc)) {
                    if(map[nr][nc]==1) {
                        q.add(new Pos(nr,nc));
                        map[nr][nc]=cnt;
                    }
                }
            }
        }
    }

    public static boolean inrange(int r, int c) {
        if(r<0 || r>=R || c<0 || c>=C) {
            return false;
        }
        return true;
    }
}
