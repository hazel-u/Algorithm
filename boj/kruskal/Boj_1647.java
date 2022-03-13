package boj.kruskal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1647 {
    static int N,M;

    static int[] parents;

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

        N = Integer.parseInt(st.nextToken()); // 집 개수
        M = Integer.parseInt(st.nextToken()); // 길 개수

        parents = new int[N+1];
        for(int i=1; i<N+1; i++) {
            parents[i]=i;
        }

        LinkedList<Road> list = new LinkedList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Road(a,b,cost));
        }

        Collections.sort(list);

        long result = 0;
        int cnt=0;

        for(Road road : list) {
            if(cnt==N-2) break;
            if(find(road.a) != find(road.b)) {
                union(road.a, road.b);
                result+=road.cost;
                cnt++;
            }
        }

        System.out.println(result);
    }

    public static int find (int house) {
        if(house == parents[house]) {
            return house;
        }
        return parents[house]=find(parents[house]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a<b) {
            parents[b]=a;
        }
        else {
            parents[a]=b;
        }
    }
}
