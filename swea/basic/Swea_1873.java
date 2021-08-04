package swea.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_1873 {
	// 상,하,좌,우
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int t =0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int nr=-1;
			int nc=-1;
			int nd=-1;
			
			char map[][] = new char[R][C];
			for(int i=0; i<R; i++) {
				String s = br.readLine();
				for(int j=0; j<C; j++) {
					map[i][j]=s.charAt(j);
					if(map[i][j]=='^') {
						nr=i;
						nc=j;
						nd=0;
					}else if(map[i][j]=='v') {
						nr=i;
						nc=j;
						nd=1;
					}else if(map[i][j]=='<') {
						nr=i;
						nc=j;
						nd=2;
					}else if(map[i][j]=='>') {
						nr=i;
						nc=j;
						nd=3;
					}
				}
			}
			
			// 시뮬레이션 시작
			int N = Integer.parseInt(br.readLine()); // 움직이는 횟수
			String s=br.readLine();
			
			map[nr][nc]='.';
			f:for(int i=0; i<N; i++) {
				switch(s.charAt(i)) {
				case 'U':
					nd = 0;
					if(nr+dr[nd]>=0 && nr+dr[nd]<R && nc+dc[nd]>=0 && nc+dc[nd]<C) { // 범위안에 있으면
						if(map[nr+dr[nd]][nc+dc[nd]]=='.') { // 다음 위치가 평지면
							nr += dr[nd];
							nc += dc[nd];
						}
					}
					break;
					
				case 'D':
					nd = 1;
					if(nr+dr[nd]>=0 && nr+dr[nd]<R && nc+dc[nd]>=0 && nc+dc[nd]<C) { // 범위안에 있으면
						if(map[nr+dr[nd]][nc+dc[nd]]=='.') { // 다음 위치가 평지면
							nr += dr[nd];
							nc += dc[nd];
						}
					}
					break;
					
				case 'L':
					nd = 2;
					if(nr+dr[nd]>=0 && nr+dr[nd]<R && nc+dc[nd]>=0 && nc+dc[nd]<C) { // 범위안에 있으면
						if(map[nr+dr[nd]][nc+dc[nd]]=='.') { // 다음 위치가 평지면
							nr += dr[nd];
							nc += dc[nd];
						}
					}
					break;
					
				case 'R':
					nd = 3;
					if(nr+dr[nd]>=0 && nr+dr[nd]<R && nc+dc[nd]>=0 && nc+dc[nd]<C) { // 범위안에 있으면
						if(map[nr+dr[nd]][nc+dc[nd]]=='.') { // 다음 위치가 평지면
							nr += dr[nd];
							nc += dc[nd];
						}
					}
					break;
					
				case 'S':
					// 벽돌로 만들어진 벽 또는 강철로 만들어진 벽이 나올 때까지 직진
					int shootR = nr;
					int shootC = nc;
					while(true) {
						shootR += dr[nd];
						shootC += dc[nd];
						if(shootR<0 || shootR >=R || shootC<0 || shootC>=C) { // 게임 맵 밖으로 나갈 때까지 직진
							break;
						}else if(map[shootR][shootC]=='*') { // 벽돌에 부딪히면 벽돌이 평지가 되고 포탄은 소멸
							map[shootR][shootC] = '.'; 
							break;
						}else if(map[shootR][shootC]=='#'){ // 강철에 부딪히면 포탄만 소멸
							break;
						}
					}
				}
			}
			
			/* 마지막 포탄위치 및 방향 map에 넣기 */
			if(nd==0) {
				map[nr][nc]='^';
			}else if(nd==1) {
				map[nr][nc]='v';
			}else if(nd==2) {
				map[nr][nc]='<';
			}else if(nd == 3) {
				map[nr][nc]='>';
			}
			
			// 출력
			bw.write("#"+(t+1)+" ");
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					bw.write(map[i][j]);
				}
				bw.write("\n");
			}
			bw.flush();
		}
	}
}
