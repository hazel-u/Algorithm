package boj.implement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Boj_2615 {
	
	public static boolean check(int[][] board, int r, int c) {
		
		// 오아, 오, 오위, 아
		int[][] d = {{1,1},{0,1},{-1,1},{1,0}};
		
		int color = board[r][c];
		
		// 확인
		for(int i=0; i<4; i++) {
			
			int count = 1;
			int next_r = r+d[i][0];
			int next_c = c+d[i][1];
			
			
			while (true) {
				if((r-d[i][0])>-1 && (r-d[i][0])<19 && (c-d[i][1])>-1 && (c-d[i][1])<19 && board[r-d[i][0]][c-d[i][1]]==color) {
					break;
				}
				
				if(count==6) {
					break;
				}
				if(next_r>-1 && next_r<19 && next_c>-1 && next_c<19 && board[next_r][next_c]==color) {
					count ++;
				}else {
					break;
				}
				next_r+=d[i][0];
				next_c+=d[i][1];
			}
			
			if(count == 5) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		int[][] board = new int[19][19];
		
		for(int r=0; r<19; r++) {
			for(int c=0; c<19; c++) {
				board[r][c] = sc.nextInt();
			}
		}
		
		boolean end = false;
		
		start : for(int r=0; r<19; r++) {
			for(int c=0; c<19; c++) {
				// 주변 확인
				boolean bingo =false;
				if(board[r][c]>0) {
					bingo = check(board, r, c);
				}
				if(bingo) {
					System.out.printf("%d\n%d %d\n",board[r][c], r+1, c+1);
					end=true;
					break start;
				}
			}
		}
		
		if(!end)
		{
			System.out.println(0);
		}
		sc.close();
	}
}
