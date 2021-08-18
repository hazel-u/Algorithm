package boj.recursive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_1992 {
	static BufferedWriter bw;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j]=s.charAt(j)-'0';
			}
		}
		
		check(N, 0, 0); // 크기, r, c
		bw.flush();
		
	}
	
	private static void check(int size, int sr, int sc) throws IOException {
		if(size==1) {
			bw.write(Integer.toString(map[sr][sc]));
		}else {
			int c = map[sr][sc];
			boolean flag=false;
			for(int i=sr; i<sr+size; i++) {
				for(int j=sc; j<sc+size; j++) {
					if(map[i][j]!=c) {
						flag=true;
						break;
					}
				}
			}
			if(flag) {
				bw.write("(");
				
				check(size/2, sr, sc);
				check(size/2, sr, sc+(size/2));
				check(size/2, sr+(size/2), sc);
				check(size/2, sr+(size/2), sc+(size/2));
				
				bw.write(")");
			}else {
				bw.write(Integer.toString(c));
			}
			
		}
	}

}
