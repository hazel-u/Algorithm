package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.StreamSupport;

public class Boj_1941 {

    static int N=5;
    static char[][] arr;
    static boolean[] visited;

    static int result=0;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Pos{
        int r;
        int c;
        Pos(int r, int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new char[N][N];
        visited = new boolean[25];

        for(int i=0; i<N; i++){
            arr[i]=br.readLine().toCharArray();
        }

        combination(0,0,0);

        System.out.println(result);
    }

    static boolean checkBFS(int[][] map, int r, int c){
        Queue<Pos> q = new LinkedList<>();

        boolean[][] tempVisited = new boolean[N][N];

        q.add(new Pos(r,c));

        int cnt=0;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int d=0; d<4; d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(nr<0 || nr>=5 || nc<0 || nc>=5) continue;
                if(map[nr][nc]==1 && !tempVisited[nr][nc]){
                    tempVisited[nr][nc]=true;
                    q.add(new Pos(nr,nc));
                    cnt++;
                }
            }
        }
        if(cnt==7) return true;
        else return false;
    }


    static void combination(int cnt, int start, int yCnt) {
        if (yCnt > 3) return;
        if (cnt == 7) {
            int[][] map = new int[5][5];
            // 다 인접해있는지 확인
            int r=0;
            int c=0;
            for (int i = 0; i < 25; i++) {
                if (visited[i]) {
                    map[i/5][i%5]=1;
                    r = i/5;
                    c = i%5;
                }
            }

            if(checkBFS(map,r,c))result++;
            return;
        }

        for (int i = start; i < 25; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(cnt + 1, i, arr[i / 5][i % 5] == 'S' ? yCnt : yCnt + 1);
                visited[i] = false;
            }
        }
    }
}
