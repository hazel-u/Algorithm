package boj.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Boj_15686 {
	static int N;
	static int M;
	static ArrayList<Pos> chicken;
	static ArrayList<Pos> house;
	static Pos[] input;
	static int min=987654321;
	
	static class Pos{
		int r;
		int c;
		public Pos(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // r
		M = Integer.parseInt(st.nextToken()); // c
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		
		
		for(int r=1; r<=N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=N; c++) {
				int next = Integer.parseInt(st.nextToken());
				if(next==2) {
					chicken.add(new Pos(r,c));
				}
				else if(next==1) {
					house.add(new Pos(r,c));
				}
			}
		} // 입력 끝
		
		input = new Pos[M];
		combination(0,0);
		System.out.println(min);
		
	}
	
	public static void combination(int cnt, int start) {
		if(cnt==M) {
			int res=0;
			for(int h=0; h<house.size(); h++) {
				int h_r = house.get(h).r;
				int h_c = house.get(h).c;
				int temp=987654321;
				
				for(int c=0; c<input.length; c++) {
					int c_r=input[c].r;
					int c_c=input[c].c;
					
					int d = Math.abs(h_c-c_c)+Math.abs(h_r-c_r);
					temp = Math.min(temp, d);
				}
				res+=temp;
			}
			min = Math.min(res, min);
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			input[cnt]=chicken.get(i);
			combination(cnt+1, i+1);
		}
	}
}
