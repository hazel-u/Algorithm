package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2146 {

    static int N;
    static int[][] map;
    static int minLength = Integer.MAX_VALUE;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Pos{
        int r;
        int c;
        int num;
        int length;

        Pos(int r, int c, int num, int length){
            this.r=r;
            this.c=c;
            this.num=num;
            this.length=length;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        /*
            1. 일단 섬별로 숫자를 부여해야해 -> 1번에서 출발했다가 1번에 도착했는데 그걸 연결된 걸로 생각할 수 있으니까
            2. 그리고 섬의 가장자리의 위치를 저장할 Queue를 만들어서 저장해
                -> 1번을 하는 것과 동시에 2번도 하자
                -> 섬의 가장 자리 = 옆에 바다를 끼고 있으면 가장자리임
            3. Queue에서 하나씩 꺼내가며 각 위치마다 가장 가까운 섬과 연결된 다리의 길이를 구해
                -> 또 다른 Queue를 만들어서 해당 위치에서 다리를 놓는 작업을 하자
                -> 즉, 가장자리의 위치들을 저장한 큐 따로, temporary하게 사용할 큐 따로
            4. 다리의 가장 짧은 길이를 구할 변수 minLength를 Integer.MAX_VALUE로 초기화해놓고 갱신시킴
                -> 갱신시키는 타이밍은 섬에서 출발한 다리가 다른 섬에 도착했을 때
                -> 바다일 때는 계속 다른 섬을 찾아

         */

        // 섬별로 숫자 부여(2 ~ ) & 큐에 섬의 가장자리 위치 저장
        Queue<Pos> q = findEdge();

        // 다리 연결
        connect(q);

        System.out.println(minLength);
    }

    public static Queue<Pos> findEdge(){
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int island=2; // 섬 번호는 2부터 시작

        Queue<Pos> tempQ = new LinkedList<>();
        // bfs를 돌며 1인 곳을 찾아서 확장
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j]==1){
                    tempQ.clear();
                    tempQ.offer(new Pos(i,j,island,0));
                    map[i][j]=island;
                    while(!tempQ.isEmpty()){
                        Pos cur = tempQ.poll();
                        int r = cur.r;
                        int c = cur.c;
                        int num = cur.num;

                        for(int d=0; d<4; d++) {
                            int nr = r+dr[d];
                            int nc = c+dc[d];

                            if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                            else if(map[nr][nc]==0 && !visited[r][c]){
                                visited[r][c]=true;
                                q.offer(new Pos(r,c,island,0)); // 가장 자리 저장
                            }else if(map[nr][nc]==1){
                                map[nr][nc]=island;
                                tempQ.offer(new Pos(nr,nc,num,0));
                            }
                        }
                    }
                    island++;
                }
            }
        }
        return q;
    }

    public static void connect(Queue<Pos> q){

        while(!q.isEmpty()){
            boolean[][] visited = new boolean[N][N];
            Queue<Pos> tempQ = new LinkedList<>();

            // 가장자리 위치 꺼내기
            Pos start = q.poll();

            // 가장자리 visited 처리
            visited[start.r][start.c]=true;

            //다리 연결
            tempQ.offer(start);

            w:while(!tempQ.isEmpty()){
                Pos cur = tempQ.poll();

                for(int d=0; d<4; d++){
                    int nr = cur.r+dr[d];
                    int nc = cur.c+dc[d];

                    if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                    else if(map[nr][nc]==0 && !visited[nr][nc]){
                        visited[nr][nc]=true;
                        tempQ.offer(new Pos(nr,nc,start.num,cur.length+1));
                    }
                    else if(map[nr][nc]>=2 && start.num!=map[nr][nc]){
                        minLength = Math.min(minLength,cur.length);
                        break w;
                    }
                }
            }
        }
    }
}
