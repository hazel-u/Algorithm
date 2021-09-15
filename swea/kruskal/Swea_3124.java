package swea.kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Swea_3124 {
	
	static int V,E;
	static int[] parent;
	
	static class Info{
		int start;
		int end;
		int value;
		
		public Info(int start, int end, int value) {
			this.start = start;
			this.end = end;
			this.value = value;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parent = new int[V+1];
			for(int v=0; v<=V; v++) {
				parent[v]=v;
			}
			
			ArrayList<Info> list = new ArrayList<>();
			
			for(int e=0; e<E; e++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Info(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			
			Collections.sort(list, new Comparator<Info>() {
				@Override
				public int compare(Info o1, Info o2) {
					return o1.value-o2.value;
				}
			});
			
			long answer=0;
			
			for(int e=0; e<E; e++) {
				Info info = list.get(e);
				
				if(find(info.start)!=find(info.end)) {
					answer += info.value;
					union(info.start, info.end);
				}
			}
			
			System.out.println("#"+t+" "+answer);
		}
	}
	
	public static int find(int x) {
		if(x==parent[x]) return x;
		return parent[x]=find(parent[x]);
	}
	
	public static void union(int s, int e) {
		s = find(s);
		e = find(e);
		
		if(s<=e) {
			parent[e]=s;
		}else {
			parent[s]=e;
		}
	}
}
