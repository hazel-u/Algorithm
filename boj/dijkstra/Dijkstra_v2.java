package boj.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Dijkstra_v2 {
    static class Node {
        int idx;
        int cost;
        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine()); // node의 개수
        int E = Integer.parseInt(br.readLine()); // 간선의 개수
        int start = Integer.parseInt(br.readLine()); // 출발지점

        // 1. 인접 리스트를 이용한 그래프 초기화
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        // 노드의 번호가 1부터 시작하므로, 0번 인덱스 부분을 임의로 만들어 놓기만 한다.
        for(int i=0; i<V; i++) {
            graph.add(new ArrayList<>());
        }
        // 그래프에 값을 넣는다.
        for(int i=0; i<E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b,cost));
        }

        // 2
        // 방문 여부를 확인할 boolean 배열
        boolean[] visited = new boolean[V+1];
        // start 노드부터 end노드까지의 최소거리를 저장할 배열
        int[] dist = new int[V+1];

        // 3. 최소 거리 저보를 담을 배열을 초기화한다.
        for(int i=0; i<V; i++) {
            // 출발지점 외 나머지 지점까지의 최소 비용을 최대로 지정해둔다.
            dist[i] = Integer.MAX_VALUE;
        }
        // 출발 지점의 비용은 0으로 초기화한다.
        dist[start]=0;

        // 4. 다익스트라 알고리즘을 진행한다.
        // 모든 노드를 방문하면 종료하기 때문에, 노드의 개수 만큼만 반복하면 된다.
        for(int i=0; i<V; i++) {
            // 4-1. 현재 거리 비용 중 최소인 지점을 선택한다.
            // 해당 노드가 가지고 있는 현재 비용
            int nodeValue = Integer.MAX_VALUE;
            // 해당 노드의 인덱스
            int nodeIdx = 0;
            // 인덱스 0은 생각하지 않기 때문에 1부터 반복을 진행한다.
            for(int j=1; j<V+1; j++) {
                // 해당 노드를 방문하지 않았고, nodeValue값보다 작을 경우
                if(!visited[j] && dist[j]<nodeValue) {
                    nodeIdx=j;
                    nodeValue = dist[j];
                }
            }
            // 최종 선택된 노드를 방문처리 한다.
            visited[nodeIdx] = true;

            // 4-2. 해당 지점을 기준으로 인접 노드의 최소 거리 값을 갱신한다.
            for(int j=0; j<graph.get(nodeIdx).size(); j++) {
                // 인접 노드를 선택한다.
                Node adjNode = graph.get(nodeIdx).get(j);
                // 인접 노드가 현재 가지는 최소비용과
                // 현재 선택된 노드값 + 현재 노드에서 인접노드로 가는 값을 비교하여 더 작은 값으로 갱신한다.
                dist[adjNode.idx] = Math.min(dist[adjNode.idx], dist[nodeIdx]+adjNode.cost);
            }
        }
        // 5. 최소 비용을 출력한다.
        for(int i=1; i<V+1; i++) {
            if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}
