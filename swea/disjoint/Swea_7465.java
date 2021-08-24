package swea.disjoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_7465 {
	static int N,M;
	static int[] parents;
	
	private static void make() {
		for(int i=1; i<=N; i++) {
			parents[i]=i;
		}
	}

	private static int find(int a) {
		if(parents[a]==a) return a;
		
		return parents[a]=find(parents[a]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		
		parents[b] = a;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			
			make();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a,b);
			}
			
			for(int i=1; i<=N; i++) {
				find(i);
			}
			boolean[] check = new boolean[N+1];
			
			for(int i=1; i<=N; i++) {
				check[parents[i]]=true;
			}
			
			int cnt=0;
			for(int i=1; i<=N; i++) {
				if(check[i]) cnt++;
			}
			System.out.println("#"+t+" "+cnt);
		}
	}
}
