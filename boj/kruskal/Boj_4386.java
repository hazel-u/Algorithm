package boj.kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_4386 {
	
	static int N;
	static int[] parent;
	
	static class Star implements Comparable<Star>{
		int start;
		int end;
		double sr;
		double sc;
		double er;
		double ec;
		double dist;
		
		public Star(int start, int end, double sr, double sc, double er, double ec, double dist) {
			this.start=start;
			this.end=end;
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
			this.dist = dist;
		}

		@Override
		public int compareTo(Star o) {
			return (int) (this.dist-o.dist);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		LinkedList<Star> dist_list = new LinkedList<>();
		
		double[][] stars = new double[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stars[i][1]=Double.parseDouble(st.nextToken());
			stars[i][0]=Double.parseDouble(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			double sr = stars[i][0];
			double sc = stars[i][1];
			
			for(int j=i+1; j<N; j++) {
				double er = stars[j][0];
				double ec = stars[j][1];

				double width = Math.abs(sc-ec);
				double height = Math.abs(sr-er);

				double dist = Math.sqrt(width*width + height*height);
				
				dist_list.add(new Star(i,j,sr,sc,er,ec,dist));
			}
		}
		
		Collections.sort(dist_list);
		
		// 크루스칼 시작 (최소신장트리)
		parent = new int[N];
		for(int i=0; i<N; i++) {
			parent[i]=i;
		}
		double answer=0;
		
		for(int i=0; i<dist_list.size(); i++) {
			if(find(dist_list.get(i).start) != find(dist_list.get(i).end)) {
				answer += dist_list.get(i).dist;
				union(dist_list.get(i).start, dist_list.get(i).end);
			}
		}
		System.out.println(String.format("%.2f", answer));
	}
	
	public static int find(int i) {
		if(i==parent[i]) {
			return i;
		}
		return parent[i]=find(parent[i]);
	}
	
	public static void union(int start, int end) {
		start = find(start);
		end = find(end);
		
		if(start<end) {
			parent[end]=start;
		}else {
			parent[start]=end;
		}
	}
	
}
