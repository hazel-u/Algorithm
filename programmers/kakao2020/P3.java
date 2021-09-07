package programmers.kakao2020;

import java.util.ArrayList;

public class P3 {

	static class Pos{
		int r,c;
		public Pos(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	public static void main(String[] args) {
		int[][] key= {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1,1,1}, {1,1,0}, {1,0,1}};
		
		System.out.println(solution(key, lock));
	}
	
	static boolean solution(int[][] key, int[][] lock) {
		int N = lock.length;
		int M = key.length;
		int[][] map = new int[3*N][3*N];
		
		int lockCnt=0;
		
		ArrayList<Pos> keyList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[N+i][N+j]=lock[i][j];
				if(lock[i][j]==0) lockCnt++;
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<M; j++) {
				if(key[i][j]==1) keyList.add(new Pos(i,j));	
			}
		}
		
		// map돌리면서 맞춰보기
		for(int r=0; r<4; r++) {
			map = rotation(map);
			
			
			for(int i=1; i<(2*N); i++) {
				for(int j=1; j<(2*N); j++) {
					int match=0;
					for(int index=0; index<keyList.size(); index++) {
						Pos cur = keyList.get(index);
						int nr = cur.r+i;
						int nc = cur.c+j;
						if(nr>=N && nr<2*N && nc>=N && nc<2*N) {
							if(map[nr][nc]==0) match++;
							else break;
						}
					}
					if(match==lockCnt) return true;
				}
			}
		}
		return false;
	}
	
	static int[][] rotation(int[][] map){
		int N = map.length;
		int[][] r_map=new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				r_map[i][j]=map[N-j-1][i];
			}
		}
		
		return r_map;
	}
}
