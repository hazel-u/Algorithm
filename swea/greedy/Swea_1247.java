package swea.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_1247 {
	
	static int[] res;
	static boolean[] visited;
	static int N;
	static int or,oc,hr,hc;
	static int min;
	
	static class Client{
		int r,c;

		public Client(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			res = new int[N];
			visited = new boolean[N];
			min=987654321;
			
			Client[] clist = new Client[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			or = Integer.parseInt(st.nextToken());
			oc = Integer.parseInt(st.nextToken());
			hr = Integer.parseInt(st.nextToken());
			hc = Integer.parseInt(st.nextToken());
			for(int i=0; i<N; i++) {
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				clist[i] = new Client(r,c);
			}
			permu(0,clist);
			System.out.println("#"+t+" "+min);
		}
	}
	
	private static void permu(int cnt, Client[] clist) {
		if(cnt == N) {
			// 조합 완료
			int tempCnt=0;
			for(int i=0; i<N; i++) {
				if(i==0) { // 회사랑 거리 구하기
					tempCnt += Math.abs(clist[res[i]].r-or) + Math.abs(clist[res[i]].c-oc);
				}else if(i==N-1) { // 전 애랑 거리 구하고, 집이랑 거리 구하기
					tempCnt += Math.abs(clist[res[i]].r-clist[res[i-1]].r) + Math.abs(clist[res[i]].c-clist[res[i-1]].c);
					tempCnt += Math.abs(clist[res[i]].r-hr) + Math.abs(clist[res[i]].c-hc);
				}else { // 전 애랑 거리 구하기
					tempCnt += Math.abs(clist[res[i]].r-clist[res[i-1]].r) + Math.abs(clist[res[i]].c-clist[res[i-1]].c);
				}
			}
			min = Math.min(min, tempCnt);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			
			res[cnt]=i;
			visited[i]=true;
			permu(cnt+1, clist);
			visited[i]=false;
		}
	}
}
