package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_16935 {

	static int N, M, R;
	static int map[][];
	static int temp[][];
	
	public static int[][] action(int num) {
		// 1. 상하반전
		if(num==1) {
			temp = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp[i][j]=map[i][j];
				}
			}
			
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<M; j++) {
					int t = temp[i][j];
					temp[i][j]=temp[(N-1)-i][j];
					temp[(N-1)-i][j]=t;
				}
			}
		}
		// 2. 좌우 반전
		else if(num==2) {
			temp = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp[i][j]=map[i][j];
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M/2; j++) {
					int t = temp[i][j];
					temp[i][j]=temp[i][(M-1)-j];
					temp[i][(M-1)-j]=t;
				}
			}
		}
		// 3. 오른쪽으로 90도 회전
		else if(num==3) {
			temp = new int[M][N];
			
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					temp[i][j]=map[N-j-1][i];
				}
			}
		}
		// 4. 왼쪽으로 90도 회전
		else if(num==4) {
			temp = new int[M][N];
			
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					temp[i][j]=map[j][M-i-1];
				}
			}
		}
		// 5. 1->2, 2->3, 3->4, 4->1
		else if(num==5) {
			temp = new int[N][M]; // 복사할 곳
			
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<M/2; j++) {
					temp[i][j+M/2] = map[i][j]; // 1->2
					temp[i+N/2][j+M/2] = map[i][j+M/2]; // 2->3
					temp[i+N/2][j] = map[i+N/2][j+M/2]; // 3->4
					temp[i][j] = map[i+N/2][j]; // 4->1
				}
			}
		}
		// 6. 1->4, 4->3, 3->2, 2->1
		else if(num==6) {
			temp = new int[N][M];
			
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<M/2; j++) {
					temp[i+N/2][j]=map[i][j]; // 1->4
					temp[i+N/2][j+M/2]=map[i+N/2][j]; // 4->3
					temp[i][j+M/2]=map[i+N/2][j+M/2]; // 3->2
					temp[i][j]=map[i][j+M/2];
				}
			}
		}
		
		return temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			N = map.length;
			M = map[0].length;
			map = action(Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
