package swea.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Swea_1767 {
	
	static int[][] map;
	static int N;
	static ArrayList<Core> cores;
	static boolean[] visited;
	static int[] res;
	static int min_length;
	static int max_core;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Core{
		int r;
		int c;
		public Core(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine()); 
			map = new int[N][N];
			cores = new ArrayList<>();
			min_length=987654321;
			max_core = 0;
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(i!=0 && i!=N-1 && j!=0 && j!=N-1 && map[i][j]==1) {
						cores.add(new Core(i,j));
					}
				}
			}
			
			dfs(0,0,0); // 인덱스, 코어갯수, 전선 길이
			bw.write("#"+t+" "+min_length+"\n");
		}
		bw.flush();
	}
	
	static void dfs(int idx, int coreCnt, int len) {
		if(idx==cores.size()) {
			if(max_core<coreCnt) {
				max_core = coreCnt;
				min_length=len;
			}else if(max_core==coreCnt) {
				min_length = Math.min(min_length, len);
			}
			return;
		}
		
		Core now = cores.get(idx);
		int r=now.r;
		int c=now.c;
		
		for(int d=0; d<4; d++) {
			// nr, nc 가능한 길 찾는 변수
			int nr = r;
			int nc = c;
			// sr, sc 전선 그리는 변수
			int sr = r;
			int sc = c;
			int count=0;
			
			while(true) {
				nr+=dr[d];
				nc+=dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=N) break; // 벽을 만나면 벗어난다.
				if(map[nr][nc]==1) { // 다른 코어나 전선을 만나면 여태까지 저장한 거리를 초기화시키고 벗어난다.
					count=0;
					break;
				}
				count++;
			}
			
			// 여태까지 저장한 거리를 전선으로 채운다.
			for(int i=0; i<count; i++) {
				sr+=dr[d];
				sc+=dc[d];
				
				map[sr][sc]=1;
			}
			
			//전선 연결이 불가능하다면 다음 코어로 이동할때 아무것도 변화없이
			if(count==0) dfs(idx+1, coreCnt, len);
			// 전선 연결이 가능하다면 다음 코어로 이동할때 연결한 코어갯수+1, 길이+count
			else {
				dfs(idx+1, coreCnt+1, len+count);
				
				// 전선 그린거 초기화
				sr = r;
				sc = c;
				for(int i=0; i<count; i++) {
					sr+=dr[d];
					sc+=dc[d];
					map[sr][sc]=0;
				}
			}
		}
	}
}
