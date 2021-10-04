package boj.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj_2143 {

	static int T,N,M;
	static int[] A;
	static int[] B;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// 누적합
		ArrayList<Integer> aList = new ArrayList<>();
		for(int i=0; i<N; i++) {
			int sum=0;
			for(int j=i; j<N; j++) {
				sum+=A[j];
				aList.add(sum);
			}
		}
		
		ArrayList<Integer> bList = new ArrayList<>();
		for(int i=0; i<M; i++) {
			int sum=0;
			for(int j=i; j<M; j++) {
				sum+=B[j];
				bList.add(sum);
			}
		}
		Collections.sort(aList);
		Collections.sort(bList);
		
		// 이분탐색 계산
		int a = 0;
		int b = bList.size()-1;
		
		long cnt=0;
		
		while(a<aList.size() && b>=0) {
			long sum = aList.get(a)+bList.get(b);
			
			if(sum==T) {
				int aValue = aList.get(a);
				int bValue = bList.get(b);
				long aCnt=0;
				long bCnt=0;
				
				// 같은 값의 갯수 구하기
				while(a<aList.size() && aList.get(a)==aValue) {
					aCnt++;
					a++;
				}
				
				while(b>=0 && bList.get(b)==bValue) {
					bCnt++;
					b--;
				}
				
				cnt+= aCnt*bCnt;
				
			}
			else if(sum<T) a++;
			else if(sum>T) b--;
		}
		
		System.out.println(cnt);
	}
}
