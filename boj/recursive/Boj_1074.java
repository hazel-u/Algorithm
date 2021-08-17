package boj.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1074 {
	
	static int N,R,C;
	static int count;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		N = (int)Math.pow(2, n);
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		find(N,R,C);
		System.out.println(count);
		
	}
	
	private static void find(int size, int r, int c) {
		if(size==1) return;
		
		if(r<size/2 && c<size/2) {
			find(size/2, r, c);
		}else if(r<size/2 && c>=size/2) {
			count+=size*size/4;
			find(size/2, r, c-size/2);
		}else if(r>=size/2 && c<size/2) {
			count += (size*size/4)*2;
			find(size/2, r-size/2, c);
		}else {
			count+=(size*size/4)*3;
			find(size/2, r-size/2, c-size/2);
		}
	}
}
