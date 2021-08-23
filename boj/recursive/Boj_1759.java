package boj.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_1759 {
	static int L,C;
	static char[] alpha;
	static char[] res;
	static String a = "aeiou";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alpha = new char[C];
		res = new char[L];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			alpha[i]=st.nextToken().charAt(0);
		}
		
		Arrays.sort(alpha);
		combi(0,0);
	
	}

	private static void combi(int cnt, int start) {
		if(cnt==L) {
			int v=0; // 모음
			int c=0; // 자음
			for(int i=0; i<L; i++) {
				if(a.contains(Character.toString(res[i]))) {
					v++;
				}else {
					c++;
				}
			}
			
			if(v>=1 && c>=2) {
				for(int i=0; i<L; i++) {
					System.out.print(res[i]);
				}
				System.out.println();
			}
			return;
		}
		
		for(int i=start; i<C; i++) {
			res[cnt]=alpha[i];
			combi(cnt+1, i+1);
		}
	}
}
