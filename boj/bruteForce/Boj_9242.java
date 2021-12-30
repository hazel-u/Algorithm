package boj.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_9242 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String Line = br.readLine();
        int colSize = Line.length();

        boolean[][] code = new boolean[5][colSize];

        for(int i=0; i<5; i++){
            if(i!=0) Line = br.readLine();
            for(int j=0; j<colSize; j++){
                code[i][j] = Line.charAt(j)!=' '?true:false;
            }
        }

        StringBuilder sb = new StringBuilder();
        int index=0;

        while(index<colSize-2){
            // 8
            if(code[0][index] && code[1][index] && code[2][index] && code[3][index] && code[4][index]
                    && code[0][index+1] && code[2][index+1] && code[4][index+1]
                    && code[0][index+2] && code[1][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("8");
            }
            // 0
            else if(code[0][index] && code[1][index] && code[2][index] && code[3][index] && code[4][index]
                    && code[0][index+1] && code[4][index+1]
                    && code[0][index+2] && code[1][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("0");
            }
            // 9
            else if(code[0][index] && code[1][index] && code[2][index] && code[4][index]
                    && code[0][index+1] && code[2][index+1] && code[4][index+1]
                    && code[0][index+2] && code[1][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("9");
            }
            // 6
            else if(code[0][index] && code[1][index] && code[2][index] && code[3][index] && code[4][index]
                    && code[0][index+1] && code[2][index+1] && code[4][index+1]
                    && code[0][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("6");
            }
            // 3
            else if(code[0][index] && code[2][index] && code[4][index]
                    && code[0][index+1] && code[2][index+1] && code[4][index+1]
                    && code[0][index+2] && code[1][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("3");
            }
            // 5
            else if(code[0][index] && code[1][index] && code[2][index] && code[4][index]
                    && code[0][index+1] && code[2][index+1] && code[4][index+1]
                    && code[0][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("5");
            }
            // 2
            else if(code[0][index] && code[2][index] && code[3][index] && code[4][index]
                    && code[0][index+1] && code[2][index+1] && code[4][index+1]
                    && code[0][index+2] && code[1][index+2] && code[2][index+2] && code[4][index+2]){
                sb.append("2");
            }
            // 4
            else if(code[0][index] && code[1][index] && code[2][index]
                    && code[2][index+1]
                    && code[0][index+2] && code[1][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("4");
            }
            // 7
            else if(code[0][index]
                    && code[0][index+1]
                    && code[0][index+2] && code[1][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("7");
            }
            // 1
            else if(code[0][index+2] && code[1][index+2] && code[2][index+2] && code[3][index+2] && code[4][index+2]){
                sb.append("1");
            }

            index+=4;
        }

        String str = sb.toString();
        int ten=1;
        int result=0;
        for(int i=str.length()-1; i>=0; i--){
            result+=(str.charAt(i)-'0')*ten;
            ten*=10;
        }

        System.out.println(result%6==0?"BEER!!":"BOOM!!");

    }
}
