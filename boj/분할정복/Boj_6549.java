package boj.분할정복;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_6549 {
	static int[] histogram;
	static int N;

	private static long getArea(int lo, int hi) {
		if(lo==hi) return histogram[lo]; // 넓이 1일 때
		
		int mid = (lo+hi)/2;
		
		/*
		 * mid를 기점으로 양쪽으로 나누어 양쪽 구간 중 큰 넓이 저장
		 * 왼쪽부분 : lo~mid
		 * 오른쪽 부분 : mid+1~hi
		 */
		long leftArea = getArea(lo, mid);
		long rightArea = getArea(mid+1, hi);
		
		// 양쪽 구간 중 큰값을 저장
		long max = Math.max(leftArea, rightArea);
		
		// 위에서 구한 max와 중간구간을 비교
		max = Math.max(max, getMidArea(lo,hi,mid));
		
		return max;
		
	}
	
	// 중간 지점 넓이 구하기
	private static long getMidArea(int lo, int hi, int mid) {
		
		// 중간지점부터 시작
		int toLeft = mid;
		int toRight = mid;
		
		long height = histogram[mid]; // 시작 높이
		
		// 초기 넓이 (어짜피 넓이가 1이라 높이가 곧 넓이가 된다)
		long max = height;
		
		// 양 끝 범위를 벗어나기 전까지 반복
		while(toLeft>lo && toRight<hi) {
			/*
			 * 양쪽 다음칸의 높이 중 높은 값을 선택하되,
			 * 갱신되는 height는 기존 height보다 작아야한다.
			 */
			// 왼쪽과 오른쪽 중 큰곳으로 전진
			if(histogram[toLeft-1] < histogram[toRight+1]) { // 오른쪽이 크면
				toRight++;
				height = Math.min(height, histogram[toRight]);
			}else { // 왼쪽이 크면
				toLeft--;
				height = Math.min(height, histogram[toLeft]);
			}
			
			max = Math.max(max, (toRight-toLeft+1)*height);
		}
		
		// 한쪽은 끝까지 가서 while문을 벗어났는데, 다른 한쪽이 끝까지 못갔을 때
		while(toRight<hi) {
			toRight++;
			height = Math.min(height, histogram[toRight]);

			max = Math.max(max, (toRight-toLeft+1)*height);
		}
		while(toLeft>lo) {
			toLeft--;
			height = Math.min(height, histogram[toLeft]);

			max = Math.max(max, (toRight-toLeft+1)*height);
		}
		
		return max;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			if(N==0) { // 탈출조건
				break;
			}
			
			histogram = new int[N];
			for(int i=0; i<N; i++) {
				histogram[i]=Integer.parseInt(st.nextToken());
			}
			
			bw.write(getArea(0,N-1)+"\n");
		}
		bw.flush();
	}
}
