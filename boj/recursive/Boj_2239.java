package boj.recursive;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Boj_2239 {

	static int[][] puzzle;
	static ArrayList<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		puzzle = new int[9][9];
		list = new ArrayList<>();
		
		for(int i=0; i<9; i++) {
			String s = br.readLine();
			for(int j=0; j<9; j++) {
				puzzle[i][j]=s.charAt(j)-'0';
				if(puzzle[i][j]==0) list.add(new int[] {i,j});
			}
		}
		
		solve(0);
	}
	
	public static void solve(int level) {
		if(level == list.size()) {
			print();
			System.exit(0);
		}
		
		int r = list.get(level)[0];
		int c = list.get(level)[1];
		
		boolean[] check = new boolean[10]; // 1~9 check
		// 행 확인
		for(int _c=0; _c<9; _c++) {
			if(puzzle[r][_c]!=0) check[puzzle[r][_c]]=true;
		}
		// 열 확인
		for(int _r=0; _r<9; _r++) {
			if(puzzle[_r][c]!=0) check[puzzle[_r][c]]=true;
		}
		// 3x3확인
		for(int _r=(r/3)*3; _r<(r/3)*3+3; _r++) {
			for(int _c=(c/3)*3; _c<(c/3)*3+3; _c++) {
				if(puzzle[_r][_c]!=0) check[puzzle[_r][_c]]=true;
			}
		}
					
		// check가 false인 숫자 넣기
		for(int i=1; i<10; i++) {
			if(!check[i]) {
				puzzle[r][c]=i;
				solve(level+1);
				puzzle[r][c]=0;
			}
		}
	}
	
	public static void print() {
		for(int r=0; r<9; r++) {
			for(int c=0; c<9; c++) {
				System.out.print(puzzle[r][c]);
			}
			System.out.println();
		}
	}
}
