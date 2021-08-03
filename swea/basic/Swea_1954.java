package swea.basic;

import java.util.Scanner;

public class Swea_1954 {
	
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i=0; i<N; i++) {
			int p = sc.nextInt();
			int cnt = 1;
			int map[][]=new int[p][p];
			boolean visited[][]=new boolean[p][p];
			
			int r=0;
			int c=-1;
			
			int d=0;
			
			while(cnt<=(p*p)) {
				
				if(r+dr[d]<0 || r+dr[d]>=p || c+dc[d]<0 ||c+dc[d]>=p || visited[r+dr[d]][c+dc[d]]) {
					d=(d+1)%4;
				}
				r=r+dr[d];
				c=c+dc[d];

				map[r][c]=cnt;
				visited[r][c]=true;
				
				cnt++;
			}
			
			System.out.println("#"+(i+1));
			for(int a=0; a<p; a++) {
				for(int b=0; b<p; b++) {
					System.out.print(map[a][b]+" ");
				}
				System.out.println();
			}
			
		}
	}
}
