package boj.페르마;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11050 {

	static int N,K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(factorial(N)/(factorial(K)*factorial(N-K)));
	}

	static int factorial(int n){
		if(n==0) return 1;
		
		return n*factorial(n-1);
	}
}
