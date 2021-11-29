package boj;
import java.io.*;
import java.util.Arrays;

public class Boj_10993 {

    static int N;
    static char[][] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int initRow = (int)Math.pow(2,N)-1;
        int initCol = (int)Math.pow(2,N+1)-3;

        arr = new char[initRow][initCol];

        draw(0,0,N);

        print(initRow, initCol);
    }

    public static void print(int row, int col) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 짝수일 때 (역삼각형)
        if(N%2==0){
            for(int r=0; r<row; r++){
                for(int c=0; c<col-r; c++){
                    bw.write(arr[r][c]=='*'?'*':' ');
                }
                bw.write('\n');
            }
        }
        // 홀수일 때 (삼각형)
        else{
            for(int r=0; r<row; r++){
                for(int c=0; c<col-row+1+r; c++){
                    bw.write(arr[r][c]=='*'?'*':' ');
                }
                bw.write('\n');
            }
        }
        bw.flush();
    }

    public static void draw(int r, int c, int level){
        if(level==1) {
            arr[r][c]='*';
            return;
        }

        int row = (int)Math.pow(2,level)-1;
        int col = (int)Math.pow(2,level+1)-3;

        // 짝수일 경우(역삼각형)
        if(level%2==0){
            for(int i=0; i<col; i++){
                arr[r][c+i]='*';
            }

            for(int i=0; i<row; i++){
                arr[r+i][c+i]='*';
                arr[r+i][c+col-1-i]='*';
            }

            draw(r+1, (int)Math.pow(2,level-1)+c, level-1);
            return;
        }
        // 홀수일 경우
        else{
            for(int i=0; i<col; i++){
                arr[r+row-1][c+i]='*';
            }

            for(int i=0; i<row; i++){
                arr[r+i][c+col/2-i]='*';
                arr[r+i][c+col/2+i]='*';
            }

            draw(r+row/2, (int)Math.pow(2,level-1)+c,level-1);
            return;
        }
    }
}
