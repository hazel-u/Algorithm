package swea.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_1210 {
	// 왼쪽, 오른쪽, 위
	static int[] dr = {0,0,-1};
	static int[] dc = {-1,1,0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int m=1; m<=10; m++) {
			int testcase = Integer.parseInt(br.readLine());
			int[][] map = new int[100][100];
			boolean[][] visited = new boolean[100][100];

			for(int i=0; i<100; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			int bingo = -1;
			for(int i=0; i<100; i++) {
				if(map[99][i]==2) {
					bingo=i;
				}
			}
			int start=-1;
			int nr = 98;
			int nc = bingo;
			visited[nr][nc]=true;
			
			w: while(true) {
				for(int d=0; d<3; d++) {
					// 도착했을때
					if(nr+dr[d]==0 && map[nr+dr[d]][nc+dc[d]]==1) {
						start=nc+dc[d];
						break w;
					}
					
					if(nr+dr[d]>-1 && nc+dc[d]>-1 && nc+dc[d]<100) { // 범위 안에 있고,
						// 그 값이 1이고, 방문하지 않은 곳이라면
						if(map[nr+dr[d]][nc+dc[d]]==1 && visited[nr+dr[d]][nc+dc[d]]==false) {
							nr=nr+dr[d];
							nc=nc+dc[d];
							visited[nr][nc]=true;
						}
						else {
							//System.out.println(map[nr+dr[d]][nc+dc[d]]);
						}
					}
				}
			}
			System.out.println("#"+testcase+" "+start);
		}
	}
}
