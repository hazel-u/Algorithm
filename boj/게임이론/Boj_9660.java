package boj.게임이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_9660 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 	dp를 적용하기에는 돌의 개수(1,000,000,000,000)만큼의 dp배열을 만들 수가 없음 -> dp 사용 x
		 	약 1000까지의 dp를 만들어서 돌려보니 n을 7로 모듈러 계산을 했을 때 나머지 값이 0또는 2가 되면 상근이가 진다는 것을 알게됨.
		 */
		
		long n = Long.parseLong(br.readLine()); // 카드 총 개수
		
		if(n%7==2 || n%7==0) System.out.println("CY");
		else System.out.println("SK");
	}

}
