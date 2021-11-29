package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_3020 {

    static int N,H;
    static int[] bot; // 석순 입력받기
    static int[] top; // 종유석 입력받기
    static int minBreak;
    static int cnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        minBreak=N;
        cnt=0;

        bot = new int[H+1];
        top = new int[H+1];

        for(int i=0; i<N/2; i++){
            bot[Integer.parseInt(br.readLine())]++;
            top[Integer.parseInt(br.readLine())]++;
        }

        solution();

        System.out.println(minBreak+" "+cnt);
    }

    public static void solution(){
        int[] botSum = new int[H+1];
        int[] topSum = new int[H+1];

        // 누적합
        for(int i=1; i<H+1; i++){
            botSum[i] = botSum[i-1]+bot[i];
            topSum[i] = topSum[i-1]+top[i];
        }

        for(int i=1; i<H+1; i++){
            int crush=0;

            crush+=botSum[H]-botSum[i-1];
            crush+=topSum[H]-topSum[H-i];

            if(crush<minBreak){
                minBreak=crush;
                cnt=1;
            }
            else if(crush==minBreak){
                cnt++;
            }
        }
    }
}
