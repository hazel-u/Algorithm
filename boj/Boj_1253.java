package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        if(N<=2){
            System.out.println(0);
            return;
        }

        Arrays.sort(arr); //정렬

        int good = 0;
        for(int n=0; n<N; n++){
            int front=0;
            int back=N-1;

            while(true){
                if(front==n) front++;
                if(back==n) back--;

                if(front>=back) break;

                int calc = arr[front]+arr[back];

                if(calc==arr[n]) {
                    good++;
                    break;
                } else if(calc < arr[n]){
                    front++;
                } else if(calc > arr[n]){
                    back--;
                }
            }
        }

        System.out.println(good);
    }
}
