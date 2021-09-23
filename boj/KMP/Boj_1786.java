package boj.KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj_1786 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] t = br.readLine().toCharArray();
		char[] p = br.readLine().toCharArray();
		
		int tLength = t.length;
		int pLength = p.length;
		
		// p의 부분일치 테이블 만들기
		int[] pi = new int[pLength]; // pi[0]은 항상 0
		// i : 접미사 포인터, j : 접두사 포인터
		for(int i=1, j=0; i<pLength; i++) {
			while(j>0 && p[i]!=p[j]) { // j의 값을 바꿔가며 최선의 방법 찾기, j>0은 j를 항상 양수로 만들어서 while안에 있는 코드에서 runtime error가 발생하지 않도록 하기 위함
				j = pi[j-1];
			}
			// p[i]와 p[j]가 같으면 둘이 일치하는 길이를 저장
			if(p[i]==p[j]) pi[i]=++j; // j는 인덱스를 뜻하고 있고, pi[i]에는 j인덱스까지의 길이를 적어야하니 ++j한것을 pi[i]에 넣는거임, pi[i]에 j+1값을 넣고 어짜피 j도 증가시켜야하니 ++j
			else pi[i]=0; // 모든 조건에 걸리지 않는다면 pi[i]는 0이 되지만, 어짜피 pi를 선언할때 0으로 초기화되기 때문에 굳이 적을 필요는 없다.
		}
		
		int cnt=0; // pattern과 같은 문자열을 몇개 찾았나
		ArrayList<Integer> list = new ArrayList<>(); // 어디서 pattern과 같은 문자열을 찾았는지 인덱스 저장
		
		// i : 텍스트 포인터, j : 패턴 포인터
		for(int i=0, j=0; i<tLength; ++i) {
			while(j>0 && t[i]!=p[j]) j=pi[j-1];
			
			if(t[i]==p[j]) {
				if(j==pLength-1) { // 일치하는 문자열 찾은거임
					cnt++;
					list.add((i+1)-pLength);
					j=pi[j]; // 이번에는 패턴이 일치해서 들어온거기때문에 pi[j-1]이 아니라 pi[j]가 j가 된다.
					/*
					 	예를 들어 pi[j]=2일 경우 0과 1 인덱스가 같다(길이가 2)는 거니 비교하는 것은 2부터 비교를 해야된다.
					 	즉, j=pi[j]로 하여 j를 비교시작점에 놓는 것이 맞다.
					 */
				}else { // 패턴 일치 중간 과정 (패턴 앞쪽 일치하며 진행중인 상황)
					j++;
				}
			}
		}
		
		System.out.println(cnt);
		for(int i=0; i<cnt; i++) {
			System.out.print((list.get(i)+1)+" "); // 문제에서는 1~tLength까지 이니까 나오는 값에 1을 더해야해
		}
	}
}
