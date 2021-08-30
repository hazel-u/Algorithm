package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_2605 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		LinkedList<Integer> list = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			int move = Integer.parseInt(st.nextToken());
			if(move==0) list.add(i+1);
			else {
				list.add(i-move,i+1);
			}
		}
		
		for(int i=0; i<N; i++) {
			System.out.print(list.get(i)+" ");
		}
	}
}
