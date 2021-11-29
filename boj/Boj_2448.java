package boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_2448 {
    static int N;
    static char[][] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][2*N-1];

        for(int i=0; i<N; i++){
            for(int j=0; j<N*2-1; j++){
                arr[i][j]=' ';
            }
        }

        draw(0,N-1, N);

        for(int i=0; i<N; i++){
            for(int j=0; j<N*2-1; j++){
                bw.write(arr[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public static void draw(int r, int c, int n){
        if(n==3){
            arr[r][c]='*';
            arr[r+1][c-1]=arr[r+1][c+1]='*';
            arr[r+2][c-2]=arr[r+2][c-1]=arr[r+2][c]=arr[r+2][c+1]=arr[r+2][c+2]='*';
            return;
        }

        draw(r,c,n/2);
        draw(r+n/2, c-n/2, n/2);
        draw(r+n/2, c+n/2, n/2);
    }
}
