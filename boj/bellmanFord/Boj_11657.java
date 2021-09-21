package boj.bellmanFord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_11657 {
	
	static int N,M;
	static long[] upper;
	static ArrayList<Info>[] list;
	
	static class Info{
		int end;
		int time;
		public Info(int end, int time) {
			this.end = end;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		// 처음에 배열로 할려고 했는데 중복되는 데이터들이 있으면 덮어써져서 값이 제대로 안나옴
		// 그래서 리스트로 변경
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			list[i]=new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			list[start].add(new Info(end, time));
		}
		
		upper = new long[N+1];
		Arrays.fill(upper, 987654321);
		
		boolean update = bellman_ford();
		
		if(update) { // 음수 싸이클이 발견되었으면
			System.out.println(-1);
		}else { // 음수 싸이클이 없다면
			for(int i=2; i<=N; i++) {
				if(upper[i]==987654321) { // 도달하지 못한 곳이라면
					System.out.println(-1);
				}else {
					System.out.println(upper[i]);
				}
			}
		}
		
	}
	
	public static boolean bellman_ford() {
		upper[1]=0;
		boolean update=false;
		
		// N-1번 돌리기
		for(int iter=1; iter<N; iter++) {
			update=false;
			for(int here=1; here<=N; here++) {
				for(Info city : list[here]) {
					if(upper[here]==987654321) break; // 아직 도달하지 못한곳이면 pass
					if(upper[city.end]>upper[here]+city.time) {
						upper[city.end]=upper[here]+city.time;
						update=true;
					}
				}
			}
			if(!update) { // update된 것이 없다면 더이상 돌리는 의미가 없다.
				return false;
			}
		}
		
		// 마지막으로 한번 더 돌려보기 -> 음수 싸이클 판별
		for(int here=1; here<=N; here++) {
			for(Info city : list[here]) {
				if(upper[here]==987654321) break;
				if(upper[city.end]>upper[here]+city.time) {
					return true; // 음수 싸이클 발견
				}
			}
		}
		
		return false;
	}
}
