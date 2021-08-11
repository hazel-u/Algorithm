package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17406 {
	static int N, M, K;
	static int[][] map;
	static Data[] r_data;
	static boolean[] isSelected;
	static Data[] p_data; // 순열 돌린 회전
	static int[] dr= {1,0,-1,0};
	static int[] dc= {0,1,0,-1};
	static int min=987654321;
	
	static class Data{
		int r;
		int c;
		int s;
		
	    public Data(int r, int c, int s) {
	        this.r = r;
	        this.c = c;
	        this.s = s;
	    }

	    @Override
	    public String toString() {
	        return "Data [r=" + r + ", c=" + c + ", s=" + s + "]";
	    }
	}
	
	public static void action(int[][] _map) {
		// p_data에서 하나씩 꺼내서 수행
		for(int k=0; k<K; k++) {
			int r = p_data[k].r-1;
			int c = p_data[k].c-1;
			int s = p_data[k].s;
			
			for(int j=s; j>0; j--) {
				int nowr = r-j;
				int nowc = c-j;
				int dir=0;
				int val = _map[nowr][nowc];
				
				while(dir<4) {
					int nr = nowr + dr[dir];
					int nc = nowc + dc[dir];
					
					if(nr<(r-j) || nr>(r+j) || nc<(c-j) || nc>(c+j)) {
						dir++;
					}else {
						_map[nowr][nowc]=_map[nr][nc];
						nowr = nr;
						nowc = nc;
					}
				}
				_map[nowr][nowc+1]=val;
			}
		}
		
		for(int i=0; i<N; i++) {
			int sum=0;
			for(int j=0; j<M; j++) {
				sum+=_map[i][j];
			}
			min = Math.min(min, sum);
		}
	}
	
	public static void permutation(int cnt) {
		if(cnt==K) {
			// 여기서 이제 돌리는 수행  + 계산 이루어짐
			int[][] _map = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					_map[i][j]=map[i][j];
				}
			}	
			action(_map);
		}
		
		for(int i=0; i<K; i++) {
			if(isSelected[i]) continue;
			
			p_data[cnt]=r_data[i];
			isSelected[i]=true;
			
			permutation(cnt+1);
			isSelected[i]=false;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		r_data = new Data[K];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			r_data[i] = new Data(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		p_data = new Data[K];
		isSelected = new boolean[K];
		
		permutation(0);
		
		System.out.println(min);
		
	}

}
