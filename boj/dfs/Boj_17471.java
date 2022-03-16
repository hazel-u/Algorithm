package boj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_17471 {
    static int N;
    static int[] popu;
    static int[] cur;
    static int min = 987654321;
    static ArrayList<Integer>[] neigh;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        popu = new int[N+1];
        cur = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            popu[i] = Integer.parseInt(st.nextToken());
        }

        neigh = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            neigh[i] = new ArrayList<>();
            for(int j=0; j<n; j++) {
                int a = Integer.parseInt(st.nextToken());
                neigh[i].add(a);
            }
        }

        // 선거구 0, 1
        backtracking(0,1);
        backtracking(1,1);

        if(min == 987654321) System.out.println(-1);
        else System.out.println(min);
    }

    public static void backtracking(int area, int depth) {
        if(depth==N+1) {
            // 계산

            // 두 선거구에 적어도 한 지역이 있나 확인
            int one=0;
            int zero=0;
            for(int i=1; i<=N; i++) {
                if(cur[i]==0) zero++;
                else one++;
            }
            if(one==0 || zero==0) return;

            // 같은 선거구에 있는 지역은 모두 연결되어있나 확인
            boolean[] visited = new boolean[N+1];
            int checkCnt = 0;
            for(int i=1; i<=N; i++) {
                if(!visited[i]) {
                    int mine = cur[i];
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i);

                    while(!q.isEmpty()) {
                        int cur_node = q.poll();
                        checkCnt++;
                        for (int next : neigh[cur_node]) {
                            if (!visited[next] && cur[next]==mine) {
                                visited[next] = true;
                                q.add(next);
                            }
                        }
                    }
                }
            }
            if(checkCnt!=N) return;

            one=0;
            zero=0;
            for(int i=1; i<=N; i++) {
                if(cur[i]==0) zero += popu[i];
                else one += popu[i];
            }
            System.out.println(Arrays.toString(cur)+" "+zero+" "+one);

            min = Math.min(min, Math.abs(zero-one));

            return;
        }

        cur[depth]=area;
        backtracking(0, depth+1);
        backtracking(1, depth+1);
    }
}
