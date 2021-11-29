package boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_2213 {

    static int N;
    static int[] weight;

    static boolean[] selected, visited;

    static ArrayList<Integer>[] list,tree;

    static int maxV=0;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        weight = new int[N+1];
        selected = new boolean[N+1];
        visited = new boolean[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) weight[i]=Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        tree = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            list[i]=new ArrayList();
            tree[i]=new ArrayList();
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        makeTree(1,-1);

        int t1 = dp(1,1); // 1을 선택
        int t0 = dp(1,0); // 1을 선택하지 않음

        if(t1>t0){
            selected[1]=true;
        }else{
            selected[1]=false;
        }
        bw.write(Math.max(t1,t0)+"\n");

        trace(1,selected[1]);

        while(!pq.isEmpty()){
            bw.write(pq.poll()+" ");
        }
        bw.flush();
    }

    public static void trace(int cur, boolean select){
        if(select){
            pq.offer(cur);
            for(int next : tree[cur]){
                trace(next,false);
            }
        }else{
            for(int next : tree[cur]){
                trace(next, selected[next]);
            }
        }
    }

    public static int dp(int cur, int select){
        int ans = 0;
        if(select==1){ // cur을 선택했다면
            for(int next : tree[cur]){
                // 자식 노드는 선택할 수 없음
                ans += dp(next, 0);
            }
            return weight[cur]+ans;
        }
        else{ // cur을 선택안했다면
            for(int next : tree[cur]){
                // 자식 노드를 선택하거나
                int t1 = dp(next,1);
                // 자식 노드를 선택하지 않거나
                int t0 = dp(next, 0);

                if(t1>t0){ // 자식 노드를 선택하는 것이 더 크면
                    selected[next]=true; //자식노드 선택
                }
                else{ // 자식 노드를 선택하지 않는 것이 더 크면(t1과 t0가 같으면)
                    selected[next]=false;
                }
                ans+=Math.max(t1,t0);
            }
            return ans; // cur은 선택안했으니 더해서 보내지 않음
        }
    }

    public static void makeTree(int now, int parent){
        for(int child : list[now]){
            if(parent!=child){
                tree[now].add(child);
                makeTree(child,now);
            }
        }
    }
}
