package basic.segmentTree;

import java.util.Arrays;

public class SegmentTreeTest {

	static int n;
	static int[] segArr;
	static int[] arr = {0,5,4,8,7,666,1};
	public static void main(String[] args) {
		/*
		 	최소 구간 트리
		 	- 1차원 array를 구간별로 나눠서 구간 별 최소값을 구하는 알고리즘
		 	
		 	- 구간 나누기
		 		n=6
		 		0~5
		 		0~2	3~5
		 		0~1	1~2	3~4	4~5
		 		0	1	2	3	4	5
		 		-> 총 13개의 구간이 나눠짐
		 		해당 구간 별 최소값을 배열에 저장
		 		
		 	- 최소값을 저장하는 배열 선언
		 		n이 2의 거듭제곱일 경우에는 2n크기의 배열이면 충분하다.
		 		하지만 위의 예시(n=6)와 같이 n이 2의 거듭제곱이 아니라면 2n크기의 배열은 부족하다.
		 		따라서, 가장 가까운 2의 거듭제곱으로 n을 올림한 뒤, 2를 곱해야한다. -> 8*2 = 16크기의 배열 선언
		 		위가 귀찮으면 n에 4를 곱하는 방법도 있긴 하지만 메모리 낭비가 될 수도 있다.
		 */
		
		n=6;
		
		segArr = new int[8*2];
		Arrays.fill(segArr, Integer.MAX_VALUE);
		init(1,1,n);
		
		System.out.println(query(1,1,n,1,3)); // arr[1]~arr[3]중 가장 작은 값 찾기
		System.out.println(query(1,1,n,1,n)); // arr[0]~arr[5]중 가장 작은 값 찾기
	}

	public static int init(int idx, int start, int end) {
		int mid = (start+end)/2;
		if(start==end) return segArr[idx]=arr[start];
		else return segArr[idx] = Math.min(init(idx*2, start, mid), init(idx*2+1, mid+1, end));
	}
	
	public static int query(int idx, int start, int end, int fs, int fe) {
		if(fe<start || fs>end) return 987654321;
		else if(fs<=start && end<=fe) return segArr[idx]; // 목표 start보다 start가 작고, 목표 end보다 end가 크면, return 그 범위의 segArr[idx]
		else {
			int mid = (start+end)/2;
			return Math.min(query(idx*2, start, mid, fs, fe), query(idx*2+1, mid+1, end, fs, fe));
		}
	}
}
