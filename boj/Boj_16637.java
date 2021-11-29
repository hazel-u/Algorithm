package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_16637 {
    static int N;
    static char[] str;

    static int max=Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = br.readLine().toCharArray();

        solution(2,str[0]-'0');

        System.out.println(max);
    }

    public static int calcX(int index, int result){
        // 괄호가 적용안된 결과값 구하기
        int newResult = result;

        switch (str[index-1]){
            case '+':
                newResult += str[index]-'0';
                break;
            case '-':
                newResult -= str[index]-'0';
                break;
            case '*':
                newResult *= str[index]-'0';
                break;
        }

        return newResult;
    }

    public static int calcO(int index, int result){
        // 괄호 적용한 값 구하기
        int newResult = result;

        switch (str[index-1]){
            case '+':
                newResult += calcX(index+2, str[index]-'0');
                break;
            case '-':
                newResult -= calcX(index+2, str[index]-'0');
                break;
            case '*':
                newResult *= calcX(index+2, str[index]-'0');
                break;
        }

        return newResult;
    }

    public static void solution(int index, int result){
        if(index==N+1){
            max = Math.max(max, result);
            return;
        }

        if(index+2<=N+1) {
            int newResult = calcX(index, result);
            solution(index+2, newResult); // 괄호 적용 x
        }
        if(index+4<=N+1) {
            int newResult = calcO(index, result);
            solution(index+4, newResult); // 괄호 적용 o
        }

    }
}
