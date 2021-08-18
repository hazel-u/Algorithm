package swea.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Swea_4012 {
	private static boolean[] visited;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			min=98765421;
			int N = Integer.parseInt(br.readLine());
			int[][] S = new int[N][N];
			visited = new boolean[N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					S[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			combination(S, N, 0, 0);
			
			System.out.println("#"+t+" "+min);
			
		}
	}

	private static void combination(int[][] S, int N, int start, int cnt) {
		if(cnt==N/2) {
			int count = solve(S);
			min = Math.min(min, count);
			return;
		}
		
		for(int i=start; i<N; i++) {
			visited[i]=true;
			combination(S, N, i+1, cnt+1);
			visited[i]=false;
		}
	}
	
	
	private static int solve(int[][] S) {
		ArrayList<Integer> q1 = new ArrayList<Integer>();
		ArrayList<Integer> q2 = new ArrayList<Integer>();
		
		for(int i=0; i<visited.length; i++) {
			if(visited[i]) q1.add(i);
			else q2.add(i);
		}
		
		int sum1=0;
		int sum2=0;
		
		for(int i=0; i<q1.size()-1; i++) {
			for(int j=i+1; j<q1.size(); j++) {
				sum1+=S[q1.get(i)][q1.get(j)]+S[q1.get(j)][q1.get(i)];
				sum2+=S[q2.get(i)][q2.get(j)]+S[q2.get(j)][q2.get(i)];
			}
		}
		
		return Math.abs(sum1-sum2);
	}
}
