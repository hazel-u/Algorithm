package swea.swea_5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
public class Swea_5215_dfs {
    // dfs
      
    static int maxScore;
    static int[][] food;
    static int N;
    static int L;
      
    public static void dfs(int cnt, int score, int cal) {
        if(cal>L) {
            return;
        }
          
        if(score>maxScore) {
            maxScore=score;
        }
          
        if(cnt==N) {
            return;
        }
          
        // 다음꺼를 포함
        dfs(cnt+1, score+food[cnt][0], cal+food[cnt][1]);
        // 다음꺼 미포함
        dfs(cnt+1, score, cal);
          
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
              
            dfs(0,0,0); // cnt, score, cal
              
              
            System.out.println("#"+t+" "+maxScore);
        }
    }
}