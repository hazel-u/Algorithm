package swea.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_1974 {
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			map = new int[9][9];
			for(int i=0; i<9; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<9; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int res = solve();
			
			System.out.println("#"+t+" "+res);
			
		}
		
	}
	
	public static int solve() {
		
		// 가로 조사
		for(int i=0; i<9; i++) {
			boolean[] chk=new boolean[10];
			for(int j=0; j<9; j++) {
				if(chk[map[i][j]]==true) {
					return 0;
				}else {
					chk[map[i][j]]=true;
				}
			}
		}
		
		// 세로 조사
		for(int i=0; i<9; i++) {
			boolean[] chk=new boolean[10];
			for(int j=0; j<9; j++) {
				if(chk[map[j][i]]==true) {
					return 0;
				}else {
					chk[map[j][i]]=true;
				}
			}
		}
		
		for(int n=0; n<9; n=n+3) {
			for(int m=0; m<9; m=m+3) {
				boolean[] chk=new boolean[10];
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(chk[map[n+i][m+j]]==true) {
							return 0;
						}else {
							chk[map[n+i][m+j]]=true;
						}
					}
				}
			}
		}
		
		return 1;
	}

}
