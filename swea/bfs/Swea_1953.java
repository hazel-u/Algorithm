package swea.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_1953 {
	
	static int N,M,R,C,L;
	static int[][] map;
	
	static String[] type = {"", "0123","01","23","03","13","12","02"};
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static boolean[][] visited;
	
	static class Pos{
		int r;
		int c;
		int time;
		public Pos(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 배열 행 크기
			M = Integer.parseInt(st.nextToken()); // 배열 열 크기
			R = Integer.parseInt(st.nextToken()); // 시작 행 위치
			C = Integer.parseInt(st.nextToken()); // 시작 열 위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간
			
			visited = new boolean[N][M];
			map = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bw.write("#"+t+" "+bfs()+"\n");
		}
		bw.flush();
	}
	
	public static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(R,C,1));
		visited[R][C]=true;
		
		int[] timeArr = new int[L+1];
		timeArr[1]=1;
		
		int res=1;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int time = cur.time;
			if(time==L) break;
			
			int dir = map[r][c];
			
			for(int d=0; d<type[dir].length(); d++) {
				int nd = type[dir].charAt(d)-'0';
				int nr = r + dr[nd];
				int nc = c + dc[nd];
				
				// 범위 확인
				if(nr>=0 && nr<N && nc>=0 && nc<M) {
					if(!visited[nr][nc] && map[nr][nc]!=0) { // 방문한 적이 없고, 터널이 있는 곳이면
						// 상, 하, 좌, 우 별로 나랑 연결 가능한지 확인
						if(nd==0 && type[map[nr][nc]].contains("1")) { // 위로 이동 가능
							visited[nr][nc]=true;
							q.offer(new Pos(nr,nc,time+1));
							res++;
						}else if(nd==1 && type[map[nr][nc]].contains("0")) { // 아래로 이동 가능
							visited[nr][nc]=true;
							q.offer(new Pos(nr,nc,time+1));
							res++;
						}else if(nd==2 && type[map[nr][nc]].contains("3")) { // 왼쪽으로 이동 가능
							visited[nr][nc]=true;
							q.offer(new Pos(nr,nc,time+1));
							res++;
						}else if(nd==3 && type[map[nr][nc]].contains("2")) { // 오른쪽으로 이동 가능
							visited[nr][nc]=true;
							q.offer(new Pos(nr,nc,time+1));
							res++;
						}
					}
				}
			}
		}
		return res;
	}
}
