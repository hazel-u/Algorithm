package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_20361 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 종이컵의 개수
		int X = Integer.parseInt(st.nextToken()); // 초기 간식의 위치(왼쪽에서부터 몇번째 칸에 있나)
		int K = Integer.parseInt(st.nextToken()); // 컵의 위치를 바꾸는 횟수
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a==X) X=b;
			else if (b==X) X=a;
		}
		System.out.println(X);
	}
}
