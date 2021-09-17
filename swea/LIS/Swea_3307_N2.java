package swea.LIS;

import java.util.Scanner;

public class Swea_3307_N2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			int[] LIS = new int[N]; // 각 원소를 끝으로 하는 최장길이
			
			for(int i=0; i<N; i++) {
				arr[i]=sc.nextInt();
			}
			
			int max = 0; // 전체 중에 최대 길이 구하기
			for(int i=0; i<N; i++) {
				LIS[i]=1;
				for(int j=0; j<i; j++) { // 내 앞에 있는 애들이랑 다 비교
					if(arr[j]<arr[i] && LIS[i]<LIS[j]+1) { // 내 앞에 있는 애가 나보다 작아야해
						LIS[i]=LIS[j]+1;
					}
				}// i를 끝으로 하는 최장길이 값 계산 완료
				if(max<LIS[i]) max = LIS[i];
			}
			System.out.println("#"+t+" "+max);
		}
	}
}
