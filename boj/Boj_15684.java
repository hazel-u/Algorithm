package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_15684 {

    static int N; // 세로선의 개수
    static int M; // 처음 놓인 가로선의 개수
    static int H; // 가로선의 개수

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H+1][N];

        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            map[a][b]=1;
            map[a][b+1]=-1;
        }

        // 홀수 개의 가로선이 연결된 세로선이 3개보다 많다면 불가능한 테스트케이스
        //if(countOdd()>3){
          //  System.out.println(-1);
            //return;
        //}

        for(int maxcnt=0; maxcnt<=3; maxcnt++){
            if(dfs(0,0,0,maxcnt)){
                System.out.println(maxcnt);
                return;
            }
        }
        System.out.println(-1);
    }

    static public boolean dfs(int r, int c, int cnt, int maxcnt){
        if(cnt==maxcnt){
            if(isAllConnected()) return true;
            return false;
        }

        for(int _r=r; _r<H; _r++){
            for(int _c=c; _c<N-1; _c++){
                if(map[_r][_c]!=0 || map[_r][_c+1]!=0) continue;
                map[_r][_c]=1;
                map[_r][_c+1]=-1;
                if(dfs(_r,_c+2,cnt+1,maxcnt)) return true;
                map[_r][_c]=0;
                map[_r][_c+1]=0;
            }
            c=0;
        }
        return false;
    }

    private static boolean isAllConnected() {

        for(int i=0; i<N; i++){
            int r=0;
            int c=i;
            while(r<H){
                if(map[r][c]==1) c++;
                else if(map[r][c]==-1) c--;
                r++;
            }
            if(c!=i) return false;
        }
        return true;
    }

    static public int countOdd(){
        int count=0;
        for(int c=0; c<N; c++){
            int _cnt=0;
            for(int r=0; r<H; r++){
                if(map[r][c]==1) _cnt++;
            }
            if(_cnt%2==1) count++;
        }

        return count;
    }
}
