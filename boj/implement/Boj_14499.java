package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_14499 {

	static int N,M,K;
	static int[][] d = {{0,0},{0,1},{0,-1},{-1,0},{1,0}}; // 동,서,북,남
	static int[] dice;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dice = new int[6]; 
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int nr = r;
		int nc = c;
		st = new StringTokenizer(br.readLine()); // K
		for(int k=0; k<K; k++) {
			int dice_dir = Integer.parseInt(st.nextToken());
			nr += d[dice_dir][0];
			nc += d[dice_dir][1];
			
			if(nr>=0 && nr<N && nc>=0 && nc<M) { //범위 안에 있으면
				move(dice_dir);
				
				// 이동한 칸에 쓰여져있는 수가 0이면 주사위 바닥면에 쓰여있는 수가 복사
				if(map[nr][nc]==0) {
					map[nr][nc]=dice[5];
				}
				
				// 이동한 칸에 쓰여져있는 수가 0보다 크면 칸에 쓰여있는 수가 주사위 바닥으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
				else if(map[nr][nc]>0){
					dice[5]=map[nr][nc];
					map[nr][nc]=0;
				}
				
				System.out.println(dice[0]);
			}
			else {
				nr -= d[dice_dir][0];
				nc -= d[dice_dir][1];
			}
		}
	}
	
	private static void move(int dir) {
		int[] temp = dice.clone();
		
		if(dir==1) {
			dice[2]=temp[5];
			dice[5]=temp[3];
			dice[0]=temp[2];
			dice[3]=temp[0];
		}else if(dir==2) {
			dice[3]=temp[5];
			dice[5]=temp[2];
			dice[0]=temp[3];
			dice[2]=temp[0];
		}else if(dir==3) {
			dice[1]=temp[5];
			dice[0]=temp[1];
			dice[4]=temp[0];
			dice[5]=temp[4];
		}else if(dir==4) {
			dice[4]=temp[5];
			dice[0]=temp[4];
			dice[1]=temp[0];
			dice[5]=temp[1];
		}
	}
}
