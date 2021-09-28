package boj.분할정복;

import java.util.Scanner;

public class Boj_10830 {

	static int T = 1000; // 큰 숫자를 1000으로 나눈 나머지를 구하기 위해
	static int[][] m;
	static int N;
	static long B;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		B = scan.nextLong();
		m=new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				m[i][j]=scan.nextInt()%T;
			}
		}
		
		// 로직 시작
		int[][] mm = matrix(B);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(mm[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static int[][] matrix(long y) {
		int[][] res=new int[N][N];
		for (int i = 0; i < N; i++) {
			res[i][i]=1;
		}
		
		while(y>0L){
			if(y%2L==1L){
				res=mul(res,m);
			}
			y=y/2L;
			m=mul(m,m);
		}
		return res;
	}
	public static int[][] mul(int[][] r, int[][] x) {
		int[][] res=new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int t=0;
				for (int k = 0; k < N; k++) {
					t=(t%T+(r[i][k]*x[k][j])%T)%T;
				}
				res[i][j]=t%T;
			}
		}
		return res;
	}
}
