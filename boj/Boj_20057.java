package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_20057 {

    static int[] percent={1,1,2,2,5,7,7,10,10};
    // 왼쪽, 아래, 오른쪽, 위
    static int[] dr = {0,1,0,-1};
    static int[] dc = {-1,0,1,0};

    static int[][] moveR = {{-1,1,-2,2,0,-1,1,-1,1},{-1,-1,0,0,2,0,0,1,1},{-1,1,-2,2,0,-1,1,-1,1},{1,1,0,0,-2,0,0,-1,1}};
    static int[][] moveC = {{1,1,0,0,-2,0,0,-1,-1},{-1,1,-2,2,0,-1,1,-1,1},{-1,-1,0,0,2,0,0,1,1},{-1,1,-2,2,0,-1,1,-1,1}};

    static int[][] map;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());
    }
    static void print(int r, int c){
        for(int i=0;i<N; i++){
            for(int j=0; j<N; j++){
                if(r==i && c==j) System.out.print("# ");
                else System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    static int move(){
        int result=0;

        int r = N/2;
        int c = N/2;

        int index=0;

        int Y = 0;
        int remainder=0;

        int temp_nc=0;
        int temp_nr=0;

        while(true){
            index+=1;

            // 왼쪽 움직이기
            for(int in=0; in<index; in++) {
                r += dr[0];
                c += dc[0];
                // 끝났는지 확인
                if (r < 0) r = 0;
                if (c < 0) c = 0;
                Y = map[r][c];
                map[r][c] = 0;
                remainder = Y;
                // 뿌리기
                for (int i = 0; i < 9; i++) {
                    int nr = r + moveR[0][i];
                    int nc = c + moveC[0][i];

                    int temp = (int) (Y * (0.01 * percent[i]));
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) result += temp;
                    else map[nr][nc] += temp;
                    remainder -= temp;
                }
                temp_nc = c - 1;
                if (temp_nc < 0 || temp_nc >= N) result += remainder;
                else map[r][temp_nc] += remainder;
            }
            if(r==0 && c==0) break;

            // 아래 움직이기
            for(int in=0; in<index; in++) {
                r += dr[1];
                c += dc[1];
                Y = map[r][c];
                map[r][c] = 0;
                remainder = Y;
                // 뿌리기
                for (int i = 0; i < 9; i++) {
                    int nr = r + moveR[1][i];
                    int nc = c + moveC[1][i];

                    int temp = (int) (Y * (0.01 * percent[i]));
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) result += temp;
                    else map[nr][nc] += temp;
                    remainder -= temp;
                }
                temp_nr = r + 1;
                if (temp_nr < 0 || temp_nr >= N) result += remainder;
                else map[temp_nr][c] += remainder;
            }

            index+=1;
            // 오른쪽 움직이기
            for(int in=0; in<index; in++) {
                r += dr[2];
                c += dc[2];
                Y = map[r][c];
                map[r][c] = 0;
                remainder = Y;
                // 뿌리기
                for (int i = 0; i < 9; i++) {
                    int nr = r + moveR[2][i];
                    int nc = c + moveC[2][i];

                    int temp = (int) (Y * (0.01 * percent[i]));
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) result += temp;
                    else map[nr][nc] += temp;
                    remainder -= temp;
                }
                temp_nc = c + 1;
                if (temp_nc < 0 || temp_nc >= N) result += remainder;
                else map[r][temp_nc] += remainder;
            }


            // 위 움직이기
            for(int in=0; in<index; in++) {
                r += dr[3];
                c += dc[3];
                Y = map[r][c];
                map[r][c] = 0;
                remainder = Y;
                // 뿌리기
                for (int i = 0; i < 9; i++) {
                    int nr = r + moveR[3][i];
                    int nc = c + moveC[3][i];

                    int temp = (int) (Y * (0.01 * percent[i]));
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) result += temp;
                    else map[nr][nc] += temp;
                    remainder -= temp;
                }
                temp_nr = r - 1;
                if (temp_nr < 0 || temp_nr >= N) result += remainder;
                else map[temp_nr][c] += remainder;
            }
        }

        return result;
    }
}
