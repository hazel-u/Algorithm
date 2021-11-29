package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Boj_21610 {

    static int N;
    static int M;

    static int[] dr = {0,0,-1,-1,-1,0,1,1,1};
    static int[] dc = {0,-1,-1,0,1,1,1,0,-1};

    static int[][] A;

    static class Cloud{
        int r;
        int c;
        Cloud(int r, int c){
            this.r=r;
            this.c=c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                A[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        List<Cloud> clouds = new ArrayList<>();
        clouds.add(new Cloud(N,1));
        clouds.add(new Cloud(N,2));
        clouds.add(new Cloud(N-1,1));
        clouds.add(new Cloud(N-1,2));

        for(int m=0; m<M; m++){ // 이동
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 움직일 방향
            int s = Integer.parseInt(st.nextToken()); // 움직일 거리

            /*
                1. 모든 구름이 d 방향으로 s칸이동한다.
                2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1증가한다.
             */
            List<Cloud> afterClouds = moveAndRain(d,s,clouds);

            /*
                3. 구름이 모두 사라진다.
                4. 2에서 물이 증가한 칸에 물복사버그 마법을 시전한다.
                    - 대각선 방향(2,4,6,8)으로 거리가 1인 칸에 물이 있는 바구니만큼 물이 증가한다.
             */
            clouds.clear();
            copyWater(afterClouds);

            /*
                5. 바구니에 저장된 물의 양이 2이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
                    - 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야한다.
             */
            clouds = makeCloud(afterClouds);
        }

        System.out.println(countWater());

    }

    static public int countWater(){
        int count=0;

        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                count+=A[r][c];
            }
        }

        return count;
    }

    static public List<Cloud> makeCloud(List<Cloud> afterClouds){
        List<Cloud> clouds = new ArrayList<>(); // 다음에 이동할 구름을 저장하기

        boolean[][] visited = new boolean[N+1][N+1];

        for(Cloud cloud:afterClouds){
            visited[cloud.r][cloud.c]=true;
        }

        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                // 바구니에 저장된 물의 양이 2이상 && 방금 구름이 사라졌던 칸이 아니면
                if(A[r][c]>=2 && !visited[r][c]){
                    A[r][c]-=2; // 바구니에 저장된 물의 양이 2 줄어들고
                    clouds.add(new Cloud(r,c)); // 구름을 생성하여 저장한다.
                }
            }
        }

        return clouds;
    }

    static public void copyWater(List<Cloud> afterClouds){
        for(Cloud cloud : afterClouds){
            int cnt=0;
            for(int d=2; d<=8; d=d+2){
                int nr = cloud.r+dr[d];
                int nc = cloud.c+dc[d];

                if(nr<=0 || nr>N || nc<=0 || nc>N) continue;
                else if(A[nr][nc]>0) cnt++;
            }
            A[cloud.r][cloud.c]+=cnt;
        }
    }

    static public List<Cloud> moveAndRain(int d, int s, List<Cloud> clouds){
        List<Cloud> afterCloud = new ArrayList<>(); // 구름이 움직인 후의 위치를 저장할 list

        for(Cloud cloud : clouds){
            int nr = cloud.r + (dr[d]*s);
            int nc = cloud.c + (dc[d]*s);

            while(nr<=0) nr+=N;
            if(nr>N) nr = (nr-1) % N + 1;
            while(nc<=0) nc+=N;
            if(nc>N) nc = (nc-1) % N + 1;

            A[nr][nc]+=1;
            afterCloud.add(new Cloud(nr,nc));
        }

        return afterCloud;
    }
}
