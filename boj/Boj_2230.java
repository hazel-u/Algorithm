package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2230 {

    static int N,M;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long min=Long.MAX_VALUE;
        for(int i=0; i<N-1; i++){
            if(min==M) break;
            for(int j=i+1; j<N; j++){
                if(Math.abs(arr[j]-arr[i])>=M){
                    min = Math.min(min,Math.abs(arr[j]-arr[i]));
                    break;
                }
            }
        }

        System.out.println(min);

    }
}
