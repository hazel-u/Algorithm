package boj.kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_21924{

    static int N,M;
    static int[] parent;

    static class Road implements Comparable<Road>{
        int a;
        int b;
        int cost;

        Road(int a, int b, int cost) {
            this.a=a;
            this.b=b;
            this.cost=cost;
        }

        @Override
        public int compareTo(Road o) {
            return this.cost-o.cost;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        LinkedList<Road> list = new LinkedList<>();

        long total_cost = 0;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            total_cost+=cost;

            list.add(new Road(a,b,cost));
        }

        Collections.sort(list);

        // 크루스칼 시작
        for(int i=0; i<N; i++) {
            parent[i]=i;
        }

        long answer_cost = 0;
        int connect=0;

        for(Road r : list) {
            if(find(r.a) != find(r.b)) {
                answer_cost += r.cost;
                union(r.a, r.b);
                connect++;
            }
        }

        if(connect==N-1) System.out.println(total_cost-answer_cost);
        else System.out.println(-1);
    }

    public static int find(int i) {
        if(i==parent[i]) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public static void union(int start, int end) {
        start = find(start);
        end = find(end);

        if(start<end) {
            parent[end]=start;
        }
        else parent[start]=end;
    }
}
