package boj;
import java.io.BufferedReader; // BufferedReader를 사용하기 위한 import
import java.io.BufferedWriter; // BufferedWriter를 사용하기 위한 import
import java.io.IOException; // IOException을 처리하기 위한 import
import java.io.InputStreamReader; // InputStreamReader를 사용하기 위한 import
import java.io.OutputStreamWriter; // OutputStreamWriter를 사용하기 위한 import
import java.util.ArrayList; // ArrayList를 사용하기 위한 import
import java.util.StringTokenizer; // StringTokenizer를 사용하기 위한 import

public class Boj_18442 { // Algo2_서울_10_유혜승 class의 시작점
	
	static int V,P,L; // 입력값을 저장할 변수 V,P,L을 static으로 선언하여 전역변수로 사용한다.
	static ArrayList<Integer> road; // 마을의 위치정보를 저장할 road를 static으로 선언하여 전역변수로 사용한다.
	static int[] res; // combination 값을 저장할 res를 전역변수로 선언한다.
	static int min; // 최소 거리를 저장할 min을 전역변수로 선언한다.
	static int[] minV; // 경찰서를 지을 위치를 저장할 minV를 전역변수로 선언한다.

	public static void main(String[] args) throws NumberFormatException, IOException { // main문 시작, 입출력시 발생할 수 있는 예외를 throws하였다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Buffer 단위로 입력을 받기 위하여 br선언
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // Buffer 단위로 출력을 하기 위하여 bw 선언
		StringTokenizer st = null; // BufferedReader로 부터 받은 한줄의 입력을 ' '단위로 짤라 사용하기 위하여 미리 선언
		
		st = new StringTokenizer(br.readLine()); // 한줄의 입력을 받아 ' '단위로 짤라 st에 저장해 놓는다.
		V = Integer.parseInt(st.nextToken()); // 마을의 개수를 V에 저장한다.
		P = Integer.parseInt(st.nextToken()); // 경찰서 개수를 P에 저장한다.
		L = Integer.parseInt(st.nextToken()); // 큰 길의 둘레를 L에 저장한다.
		
		res = new int[P]; // combination에서 사용할 res배열을 P크기만큼 선언해 놓는다. res배열에는 경찰서를 지을 위치가 저장된다.
		min=987654321; // 최소 거리를 저장할 min을 987654321로 초기화한다.
		minV = new int[P]; // 최종적으로 경찰서를 지을 위치를 저장할 minV 배열을 선언해 놓는다. 
		
		road = new ArrayList<>(); // 각 마을의 위치를 저장할 road를 ArrayList로 선언하며 초기화한다.
		st = new StringTokenizer(br.readLine()); // 한줄의 입력을 받아 ' '단위로 짤라 st에 저장해 놓는다.
		for(int v=0; v<V; v++) { // 마을의 개수(V)만큼 for문을 돌면서 마을의 위치정보를 받는다.
			int loc = Integer.parseInt(st.nextToken()); // st에 저장된 데이터들을 차례대로 불러오며 Integer로 parsing한다.
			road.add(loc); // 마을의 위치를 road에 저장한다.
		} // 마을의 위치를 저장하는 for문 끝
		
		solve(0,0); // idx 0, start 0부터 combination을 돌며 경찰서가 위치할 수 있는 마을의 조합을 찾고, 최단 거리를 구하는 함수이다.
		bw.write(min+"\n"); // 테스트 케이스와 최단 거리를 출력한다.
		for(int i=0; i<P; i++) { // 경찰서의 개수만큼 for문을 돌면서
			bw.write(minV[i]+" "); // 최단거리에 있는 경찰서의 위치를 오름차순으로 출력한다.
		} // 경찰서의 개수만큼 도는 for문 끝
		bw.write("\n"); // 출력 형식을 위하여 한 줄을 뛴다.
		bw.flush(); // Buffer에 저장된 데이터들을 한번에 출력한다.
	} // main문 끝
	
	public static void solve(int idx, int start) { // 재귀를 이용하여 경찰서가 위치할 수 있는 마을의 조합을 찾고, 최단거리를 구하는 함수이다.
		if(idx==P) { // idx==P가 되면(즉, 정해진 경찰서의 개수만큼 경찰서의 위치를 조합으로 구했을 때)
			int totalDist = 0; // 해당 조합의 최단 거리를 저장할 변수
			for(int i=0; i<V; i++) { // 마을의 개수만큼 for문을 돈다. 해당 마을에서 가장 가까운 경찰서와의 거리를 구한다.
				int near = L+1; // 해당 마을에서 가장 가까운 경찰서와의 거리를 구하는 변수. 큰 길의 둘레의 +1로 초기화를 해준다.
				for(int p=0; p<P; p++) { // 경찰서의 개수만큼 for문을 돈다.
					int dist = Math.min(Math.abs(road.get(i)-res[p]), L-Math.abs(road.get(i)-res[p])); // 해당 마을과 해당 경찰서와의 거리를 구한다.
					near = Math.min(near, dist); // 해당 마을에서 가장 가까운 경찰서와의 거리를 구한다.
				} // 가장 가까운 경찰서를 찾는 for문 끝
				totalDist+=near; // 가장 가까운 경찰서와의 거리를 totalDist에 더한다.
			}
			if(totalDist < min) { // 지금 조합에서 구한 최단 거리가 여태까지 구한 모든 조합의 최단거리보다 짧다면
				min = totalDist; // min에 지금 거리를 저장한다.
				for(int i=0; i<P; i++) { // 경찰서의 개수만큼 for문을 돌며
					minV[i]=res[i]; // 지금 조합을 최종 경찰서 위치를 저장하는 배열(minV)에 저장한다.
				} // 최종 경찰서 위치 저장하는 for문 끝
			} // 지금 조합이 최단 거리인지 판단하는 if문 끝
			return; // 해당 조합이 끝났기 때문에 다음 조합을 확인하기 위하여 return을 한다.
		} // idx==P를 확인하는 if문 끝
		
		for(int i=start; i<V; i++) { // parameter로 들어온 start부터 마을의 개수까지 for문을 돌린다.
			res[idx]=road.get(i); //  road 리스트에서 i번째에 있는 마을의 위치를 조합 배열 (res)에 넣는다.
			solve(idx+1, i+1); // 조합에 들어갈 다음 마을의 위치를 찾기 위하여 idx+1(다음 마을), i+1(현재 마을을 조합에 넣지 않게 하기 위한 start값)을 parameter로 재귀를 한다.
		} // 조합을 구하는 for문 끝
	} // solve함수 끝
} // class 끝
