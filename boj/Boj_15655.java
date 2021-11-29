package boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_15655 {

    static int N, M;
    static int[] arr;
    static int[] res;

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        res = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 정렬

        combi(0,0);

        bw.flush();
    }

    public static void print() throws Exception {
        for(int a : res){
            bw.write(a+" ");
        }
        bw.write("\n");
    }

    public static void combi(int cnt, int start) throws Exception{
        if(cnt==M){
            print();
            return;
        }

        for(int i=start; i<N; i++){
            res[cnt]=arr[i];
            combi(cnt+1, i+1);
        }
    }
}
