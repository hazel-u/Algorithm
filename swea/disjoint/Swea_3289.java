package swea.disjoint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_3289 {
	static int N,M;
	static int[] parents;
	
	private static void work() {
		for(int i=1; i<=N; i++) {
			parents[i]=i;
		}
	}
	
	private static int find(int a) {
		if(parents[a]==a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	private static void union(int a, int b) {
		if(find(a)==find(b)) return;
		
		parents[find(b)]=find(a);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			bw.write("#"+t+" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			work();
			
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine());
				int option = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(option==0) { // a와 b를 합침
					union(a,b);
				}else if(option==1) { // 두 원소가 같은 집합에 포함되어 있는지 확인
					if(a==b || find(a) == find(b)) bw.write('1');
					else bw.write('0');
				}
			}
			bw.write("\n");
		}
		bw.flush();
	}
}
