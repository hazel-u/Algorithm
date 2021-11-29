package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_12100 {

    static int N;
    static int maxV=0;

    // 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int d=0; d<4; d++){
            game(arr, 0, d); // 상하좌우
        }

        System.out.println(maxV);
    }

    public static void game(int[][] arr, int count, int dir){
        if(count==5){
            maxV = Math.max(maxV, findMaxValue(arr));
            return;
        }

        // dir에 따른 이동
        // 이미 합쳐진 블럭인지 확인하는 visited 배열
        boolean[][] visited = new boolean[N][N];
        // 이동된 블럭들을 저장할 newArr 배열, arr의 값을 복사
        int[][] newArr = copyArr(arr);
        // 이동
        if(dir==0 || dir==2){ // 위로 가거나, 왼쪽으로 갈 경우
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    // 지금 보고 있는 곳에 값이 있는 경우, 이동 시작
                    if(newArr[r][c]!=0){
                        // 블럭의 값 저장하기
                        int blockV = newArr[r][c];
                        // 원래 위치 0으로 바꾸기
                        newArr[r][c]=0;
                        // 위치를 표시할 변수 nr, nc (미리 한칸 앞으로 가 있는다.)
                        int nr = r + dr[dir];
                        int nc = c + dc[dir];

                        // 움직일 수 없을 때 까지 이동
                        while(true){
                            // 이동할 곳이 범위 밖이거나, 0이 아닌 나와 다른 숫자가 있는 경우
                            if(!inRange(nr,nc) || (newArr[nr][nc]!=0 && newArr[nr][nc]!=blockV)){
                                // 바로 전 위치에 blockV 넣기
                                newArr[nr-dr[dir]][nc-dc[dir]]=blockV;
                                break;
                            }
                            // 이동할 곳이 0인 경우 -> 계속 이동
                            else if(newArr[nr][nc]==0) {
                                nr+=dr[dir];
                                nc+=dc[dir];
                            }
                            // 이동할 곳에 나와 같은 숫자가 있는 경우
                            else if(newArr[nr][nc]==blockV){
                                // 이번 턴에 합쳐지지 않았던 곳이면 내 값*2 한 후 그 자리에 저장
                                if(!visited[nr][nc]){
                                    newArr[nr][nc]=blockV*2;
                                    // 이번 턴에 합쳐짐
                                    visited[nr][nc]=true;
                                }
                                // 이번 턴에 합쳐졌다면 그 전 칸에 내 값 저장하기
                                else{
                                    newArr[nr-dr[dir]][nc-dc[dir]]=blockV;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(dir==1){ // 아래로 갈 경우
            for(int r=N-1; r>=0; r--){
                for(int c=0; c<N; c++){
                    // 지금 보고 있는 곳에 값이 있는 경우, 이동 시작
                    if(newArr[r][c]!=0){
                        // 블럭의 값 저장하기
                        int blockV = newArr[r][c];
                        // 원래 위치 0으로 바꾸기
                        newArr[r][c]=0;
                        // 위치를 표시할 변수 nr, nc (미리 한칸 앞으로 가 있는다.)
                        int nr = r + dr[dir];
                        int nc = c + dc[dir];

                        // 움직일 수 없을 때 까지 이동
                        while(true){
                            // 이동할 곳이 범위 밖이거나, 0이 아닌 나와 다른 숫자가 있는 경우
                            if(!inRange(nr,nc) || (newArr[nr][nc]!=0 && newArr[nr][nc]!=blockV)){
                                // 바로 전 위치에 blockV 넣기
                                newArr[nr-dr[dir]][nc-dc[dir]]=blockV;
                                break;
                            }
                            // 이동할 곳이 0인 경우 -> 계속 이동
                            else if(newArr[nr][nc]==0) {
                                nr+=dr[dir];
                                nc+=dc[dir];
                            }
                                // 이동할 곳에 나와 같은 숫자가 있는 경우
                            else if(newArr[nr][nc]==blockV){
                                // 이번 턴에 합쳐지지 않았던 곳이면 내 값*2 한 후 그 자리에 저장
                                if(!visited[nr][nc]){
                                    newArr[nr][nc]=blockV*2;
                                    // 이번 턴에 합쳐짐
                                    visited[nr][nc]=true;
                                }
                                // 이번 턴에 합쳐졌다면 그 전 칸에 내 값 저장하기
                                else{
                                    newArr[nr-dr[dir]][nc-dc[dir]]=blockV;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(dir==3){ // 오른쪽으로 갈 경우
            for(int r=0; r<N; r++){
                for(int c=N-1; c>=0; c--){
                    // 지금 보고 있는 곳에 값이 있는 경우, 이동 시작
                    if(newArr[r][c]!=0){
                        // 블럭의 값 저장하기
                        int blockV = newArr[r][c];
                        // 원래 위치 0으로 바꾸기
                        newArr[r][c]=0;
                        // 위치를 표시할 변수 nr, nc (미리 한칸 앞으로 가 있는다.)
                        int nr = r + dr[dir];
                        int nc = c + dc[dir];

                        // 움직일 수 없을 때 까지 이동
                        while(true){
                            // 이동할 곳이 범위 밖이거나, 0이 아닌 나와 다른 숫자가 있는 경우
                            if(!inRange(nr,nc) || (newArr[nr][nc]!=0 && newArr[nr][nc]!=blockV)){
                                // 바로 전 위치에 blockV 넣기
                                newArr[nr-dr[dir]][nc-dc[dir]]=blockV;
                                break;
                            }
                            // 이동할 곳이 0인 경우 -> 계속 이동
                            else if(newArr[nr][nc]==0) {
                                nr+=dr[dir];
                                nc+=dc[dir];
                            }
                                // 이동할 곳에 나와 같은 숫자가 있는 경우
                            else if(newArr[nr][nc]==blockV){
                                // 이번 턴에 합쳐지지 않았던 곳이면 내 값*2 한 후 그 자리에 저장
                                if(!visited[nr][nc]){
                                    newArr[nr][nc]=blockV*2;
                                    // 이번 턴에 합쳐짐
                                    visited[nr][nc]=true;
                                }
                                // 이번 턴에 합쳐졌다면 그 전 칸에 내 값 저장하기
                                else{
                                    newArr[nr-dr[dir]][nc-dc[dir]]=blockV;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        // 다시 상하좌우로 이동
        for(int d=0; d<4; d++){
            game(newArr,count+1,d);
        }


    }

    public static boolean inRange(int r, int c){
        if(r<0 || r>=N || c<0 || c>=N) return false;

        return true;
    }

    public static int[][] copyArr(int[][] arr){
        int[][] newArr = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                newArr[i][j]=arr[i][j];
            }
        }

        return newArr;
    }

    public static int findMaxValue(int[][] arr){
        int res = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                res = Math.max(res,arr[i][j]);
            }
        }
        return res;
    }
}

