package boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_2447 {

   static char[][] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                arr[i][j]=' ';
            }
        }

        solution(0,0,N);

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                bw.write(arr[i][j]);
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public static void solution(int r, int c, int size){
        if(size==1) {
            arr[r][c]='*';
            return;
        }

        int nSize = size/3;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(i==1 && j==1) continue;

                solution(r+(nSize*i), c+(nSize*j),nSize);
            }
        }
    }
}
