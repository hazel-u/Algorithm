package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16168 {

    static boolean[][] visited;
    static boolean[][] map;

    static boolean[] visitedNode;

    static int V,E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new boolean[V][V];
        visited = new boolean[V][V];
        visitedNode = new boolean[V];

        for(int e=0; e<E; e++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            map[a][b]=true;
            map[b][a]=true;
        }

        for(int i=0; i<V; i++){
            visitedNode[i]=true;
            if(isConnected(i,1)){
                System.out.println("YES");
                return;
            }
            visitedNode[i]=false;
        }
        System.out.println("NO");
    }

    static public boolean isConnected(int node, int cnt){ // 지금 위치하는 노드번호, 지나간 연결지점의 개수
//        for(int c=0; c<cnt; c++) System.out.print("-");
//        System.out.println(node+1);
        if(cnt==E+1) {
            for(int i=0; i<V; i++){
                if(!visitedNode[i]) return false;
            }
            return true;
        }

        for(int i=0; i<V; i++){
            if(node==V) continue;
            if(map[node][i] && !visited[node][i]){ // 연결되어있고, 방문하지 않은 간선이라면
                visited[node][i]=true;
                visited[i][node]=true;
                visitedNode[i]=true;

                if(isConnected(i,cnt+1)) return true;

                visited[node][i]=false;
                visited[i][node]=false;
                visitedNode[i]=false;
            }
        }

        return false;
    }
}
