package boj.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_2309 {
	
	static int[] arr;
	static int[] res;
	static boolean finish;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new int[9];
		res = new int[7];
		
		for(int i=0; i<9; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		combination(0,0);
		
	}
	
	private static void combination(int cnt, int start) {
		if(finish) return;
		if(cnt==7) {
			int temp = 0;
			for(int i=0; i<7; i++) {
				temp+=res[i];
			}
			if(temp==100) {
				finish=true;
				Arrays.sort(res);
				for(int i=0; i<7; i++) {
					System.out.println(res[i]);
				}
			}
			return;
		}
		
		
		for(int i=start; i<9; i++) {
			res[cnt]=arr[i];
			combination(cnt+1, i+1);
		}
	}
}
