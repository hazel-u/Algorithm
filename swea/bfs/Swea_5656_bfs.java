package swea.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_5656_bfs {

	private static class Point{ // 함께 부셔질 벽돌 관리 클래스
		int r,c,cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	private static int[] dr = {-1,1,0,0};
	private static int[] dc = {0,0,-1,1};
	private static int N,W,H,min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int Tc = Integer.parseInt(in.readLine());
		
		StringTokenizer st = null;
		for(int tc=1; tc<=Tc; tc++) {
			
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			go(0, map); // 중복순열 코드를 만든 후 시뮬레이션 할 계획
			System.out.println("#"+tc+" "+min);
		}
		
	}

	// 중복순열로 구슬을 던짐
	private static void go(int cnt, int[][] map) {
		
		// 기저 조건
		if(cnt==N) { // 구슬을 다 던짐
			// 남아있는 벽돌 수를 count해서 최소값 갱신
			int result = getRemain(map);
			min = Math.min(result, min);
			
			return;
		}
		
		int[][] newMap = new int[H][W];
		for(int c=0; c<W; c++) { // 0열부터 마지막 열까지 시도
			
			// c열에 구슬이 던져졌을 때 위에서 처음 만나는 벽돌 찾기
			int r=0;
			while(r<H && map[r][c]==0) r++; // 벽돌을 만날 때까지 계속 내려가기
			
			if(r==H) { // 구슬을 던졌지만, 맞은 벽돌이 없는 경우(해당 열이 모두 빈칸일 경우)
				// 다음 구슬 던지기
				go(cnt+1, map);
			}else { // 맞은 벽돌이 있는 경우
				// 이전 cnt까지의 map의 상태를 복사하여 사용해야한다. (그냥 쓰면 다음 중복순열에 영향을 끼침)
				copy(map,newMap);
				
				// 맞은 벽돌 및 주변 벽돌을 함께 제거 (연쇄적 처리)
				bomb(newMap,r,c);
				
				// 벽돌 내리기
				//down(newMap);
				down2(newMap);
				
				// 다음 구슬 던지기
				go(cnt+1, newMap);
			}
		}
	}

	private static int getRemain(int[][] map) {
		int count=0;
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j]>0) count++;
			}
		}
		
		return count;
	}
	

	private static void down(int[][] map) {
		
		for(int c=0; c<W; c++) {
			int r=H-1;
			while(r>0) {
				if(map[r][c]==0) { // 빈칸이면 벽돌 내리기
					int nr = r-1; // 자신의 직전 행부터 탐색
					while(nr>0 && map[nr][c]==0) nr--;
					
					map[r][c]=map[nr][c]; // 찾은 벽돌을 내려야하는 빈칸에 채운다.
					map[nr][c]=0; // 내린 벽돌 자리는 빈칸으로 만들어준다.
				}
				--r;
			}
		}
	}
	
	private static ArrayList<Integer> list = new ArrayList<>();
	private static void down2(int[][] map) { // list를 활용한 내리기
		for(int c=0; c<W; c++) {
			int r;
			for(r=H-1; r>=0; --r) {
				if(map[r][c]>0) { // 부서지지 않은 벽돌만 리스트에 담기
					list.add(map[r][c]);
					map[r][c]=0; // 벽돌이 있던 자리는 빈칸(0)으로
				}
			}
			
			// 리스트에 있는 벽돌 제일 아래 행부터 채우기
			r=H-1;
			for(int b:list) map[r--][c]=b;
			list.clear();
		}
	}

	private static void bomb(int[][] map, int r, int c) {
		// BFS로 함께 부숴질 벽돌 처리
		Queue<Point> queue = new LinkedList<>();
		if(map[r][c]>1) { // 벽돌의 숫자가 1보다 클 때만 주변 벽돌 연쇄 처리
			queue.offer(new Point(r,c,map[r][c]));
		}
		map[r][c]=0; // 벽돌을 제거하고 빈칸으로 채우기
		
		Point p = null;
		while(!queue.isEmpty()) {
			p = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = p.r;
				int nc = p.c;
				
				for(int k=1; k<p.cnt; k++) {
					nr += dr[d];
					nc += dc[d];
					if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]!=0) {
						if(map[nr][nc]>1) queue.offer(new Point(nr,nc,map[nr][nc]));
						map[nr][nc]=0;
					}
				}
			}
		}
		
	}

	private static void copy(int[][] map, int[][] newMap) {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

}
