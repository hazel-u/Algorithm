package boj.basic;

import java.util.Arrays;
import java.util.Scanner;

public class Boj_2981 {
	static int gcd(int a, int b) { //최대공약수 구하기
		
		if(a%b==0) {
			return b;
		}
		
		return gcd(b, a%b);
	}


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt(); // 숫자의 갯수
		int[] nums = new int[N]; // 숫자를 담을 배열
		int[] sub = new int[N-1]; // 숫자들의 차이를 담을 배열
		
		for(int i=0; i<N; i++) {
			nums[i]=scan.nextInt();
		}
		Arrays.sort(nums);
		
		
		// sub배열 내에서 최대공약수 구하기
		int v_gcd = nums[1]-nums[0];
		
		for(int i=1; i<N-1; i++) {
			v_gcd = gcd(v_gcd, nums[i+1]-nums[i]);
		}
		
		// v_gcd의 약수 찾기
		for(int i=2; i<=v_gcd; i++) {
			if(v_gcd % i ==0) {
				System.out.println(i);
			}
		}
	}

}
