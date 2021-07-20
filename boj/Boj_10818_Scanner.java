package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Boj_10818_Scanner {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		int[] arr = new int[N];
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		for(int i=0; i<N; i++) {
			arr[i]=scan.nextInt();
		}
		Arrays.sort(arr);
		System.out.println(arr[0]+" "+arr[N-1]);
	}

}
