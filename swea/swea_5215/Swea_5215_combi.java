package swea.swea_5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
public class Swea_5215_combi {
	// 조합을 이용해서 푼 문제 -> 시간이 가장 오래걸림
      
    static int maxScore;
    static int[][] food;
    static int N;
    static int L;
    static int[] numbers;
      
    private static void combination(int R, int cnt, int start) {
          
        if(cnt == R) {
            int score=0;
            int cal=0;
            for(int i=0; i<R; i++) {
                score+=food[numbers[i]][0];
                cal+=food[numbers[i]][1];
                if(cal>L) {
                    return;
                }
            }
            if(cal<=L) {
                maxScore=Math.max(maxScore, score);
            }
            return;
        }
          
        // start 위치의 수부터 가능한 수 모두 고려
        for(int i=start; i<N; i++) { // i: 인덱스
            numbers[cnt] = i;
              
            combination(R, cnt+1, i+1);
        }
    }
  
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
          
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 재료의 수
            L = Integer.parseInt(st.nextToken()); // 제한 칼로리
            maxScore=0;
              
            food = new int[N][2];
            // 민기의 점수 T, 칼로리 K
            for(int n=0; n<N; n++) {
                st = new StringTokenizer(br.readLine());
                food[n][0]=Integer.parseInt(st.nextToken());
                food[n][1]=Integer.parseInt(st.nextToken());
            }
              
            for(int r=1; r<=N; r++) {
                numbers=new int[r];
                combination(r,0,0);
            }
              
              
            System.out.println("#"+t+" "+maxScore);
        }
    }
}
