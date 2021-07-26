package boj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2573 {
	static int N;
	static int M;
	static int year;
	static int[][] map;
	static int[][] melt;
	static int[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // row
		M = Integer.parseInt(st.nextToken()); // col
		
		map = new int[N][M];
		visited = new int[N][M];
		
		// 배열 채우기
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 녹는 정보
		melt = new int[N][M];
		
		year = 0;
		
		// 빙산 덩어리 세기(dfs)
		while(true) {
			int count=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(visited[i][j]==0 && map[i][j]!=0) {
						dfs(i,j);
						count++;
					}
				}
			}
			
			if(count==0) {
				System.out.println(0);
				break;
			}else if(count > 1) {
				System.out.println(year);
				break;
			}
			
			melting();
			year++;
			
		}
		
	}
	
	static void dfs(int r, int c) {
		visited[r][c]=1;
		
		for(int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if(nr>-1 && nr<N && nc>-1 && nc<M) {
				// 1년후에 녹는 빙산의 양 구하기
				if(map[nr][nc]==0) {
					melt[r][c]++;
				}
			}
			
			if(visited[nr][nc]==0 && map[nr][nc]!=0) {
				dfs(nr,nc);
			}
		}
	}

	static void melting() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]-=melt[i][j]; //빙하 녹이기
				
				if(map[i][j] < 0) {
					map[i][j]=0; // 빙하값이 음수가 되면 0으로 바꾸기
				}
				
				// 방문 배열 초기화
				visited[i][j]=0;
				
				// 녹는 배열 초기화
				melt[i][j]=0;
			}
		}
	}
}
