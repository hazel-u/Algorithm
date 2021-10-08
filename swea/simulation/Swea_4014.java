package swea.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_4014 {

	static int N,X;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int res =0;
			for(int i=0; i<N; i++) {
				if(solveRow(i)) res++; // row별 검사
				if(solveCol(i)) res++; // col별 검사
			}
			
			bw.write("#"+t+" "+res+"\n");
		}
		bw.flush();
	}
	
	public static boolean solveRow(int r) {
		int prev = map[r][0];
		int size = 1;
		int c = 1;
		
		while(c<N) {
			if(prev==map[r][c]) {
				size++;
				c++;
			}
			else if(map[r][c] == prev+1) { //오르막 경사로
				if(size<X) return false;
				prev++;
				size=1;
				c++;
			}else if(map[r][c] == prev-1) { //내리막 경사로
				int count=0;
				for(int k=c; k<N; k++) { // 현재 위치부터 다음을 보며 X길이만큼 경사로를 세울 수 있는지 확인
					if(map[r][k] != prev-1) break; // 높이가 바뀌면 break;
					if(++count==X) break; // X길이 만족하면 탈출
				}
				if(count<X) return false; // X길이만큼 경사로를 못세우고 나왔으니 false return
				prev--;
				size=0;
				c+=X;
			}else return false; // 높이가 2이상 차이나면 활주로 건설 불가
		}
		return true;
	}
	
	public static boolean solveCol(int c) {
		int prev = map[0][c];
		int size=1;
		int r=1;
		
		while (r<N) {
			if(prev==map[r][c]) {
				size++;
				r++;
			}else if(prev+1 == map[r][c]) { //오르막길
				if(size<X) return false;
				prev++;
				size=1;
				r++;
			}else if(prev == map[r][c]+1) { // 내리막길
				int count=0;
				for(int k=r; k<N; k++) {
					if(map[k][c]!=prev-1) break;
					if(++count==X) break;
				}
				if(count<X) return false;
				prev--;
				size=0;
				r+=X;
			} else return false;
		}
		return true;
	}

}
