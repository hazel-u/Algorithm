package swea.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Swea_3234 {
	static int sum;
	static int[] w,p_w;
	static int N;
	static boolean[] visited;
	static int possible;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			
			possible=0;
			sum=0;
			w = new int[N];
			visited = new boolean[N];
			p_w=new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				w[i]=Integer.parseInt(st.nextToken());
				sum+=w[i];
			}
			
			permutation(0);
			
			System.out.println("#"+t+" "+possible);
		}
	}
	
	private static void permutation(int cnt) {
		if(cnt==N) {
			check(0,0,0);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			
			visited[i]=true;
			p_w[cnt]=w[i];
			permutation(cnt+1);
			visited[i]=false;
			
		}
	}
	
	private static void check(int idx, int sumL, int sumR) {
		if(idx==N) {
			possible++;
			return;
		}
		
		check(idx+1, sumL+p_w[idx], sumR);
		if(sumR+p_w[idx] <= sumL) {
			check(idx+1, sumL, sumR+p_w[idx]);
		}
	}
}
