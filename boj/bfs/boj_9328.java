package boj.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class boj_9328 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Pos {
        int r;
        int c;
        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[h][w];
            ArrayList<Pos> startPos = new ArrayList<>();
            boolean[] keys = new boolean[26];

            for(int i=0; i<h; i++){
                String s = br.readLine();
                for(int j=0; j<w; j++){
                    map[i][j] = s.charAt(j);
                    if(map[i][j]!='*' && (i==0 || j==0)) {
                        startPos.add(new Pos(i,j));
                    }
                }
            }

            String key = br.readLine();
            if(key.charAt(0)!='0') {
                for (int i = 0; i < key.length(); i++) {
                    keys[(int) key.charAt(i) - 97] = true;
                }
            }

            for(Pos start : startPos) {
                Queue<Pos> q = new LinkedList<>();
                q.add(start);

                // start하는 곳 방문 처리


                while(!q.isEmpty()) {
                    // 방문 후 처리는 여기서

                    // 방문 처리는 q에 넣을 때
                }
            }

        }
    }
}
