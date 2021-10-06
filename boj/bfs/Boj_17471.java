package boj.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_17471 {

	static int min=987654321;
	static int N;
	static int[] population;
	static ArrayList<Integer>[] city;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		population = new int[N+1];
		for(int i=1; i<=N; i++) {
			population[i]=Integer.parseInt(st.nextToken());
		}
		
		city = new ArrayList[N+1];
		for(int i=0; i<=N; i++) city[i] = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 현재 구역과 인접한 구역의 수
			for(int j=0; j<n; j++) {
				city[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		//-------- 입력
		
		ArrayList<Integer> A = new ArrayList<>();
		for(int i=1; i<=N/2; i++) {
			combi(0,1,i,A);
		}
		
		if(min==987654321) System.out.println(-1);
		else System.out.println(min);
		
	}
	
	public static void print(ArrayList<Integer> A) {
		for(int i=0; i<A.size(); i++) {
			System.out.print(A.get(i)+" ");
		}
		System.out.println();
	}
	
	public static void combi(int idx, int start, int cn, ArrayList<Integer> A) {
		if(idx==cn) {
			if(isConnected(A)) { // A 선거구 내의 구역들이 모두 연결되어있는지 확인
				// B선거구 만들기
				ArrayList<Integer> B = new ArrayList<>();
				for(int i=1; i<=N; i++) {
					if(!A.contains(i)) B.add(i);
				}
				
				// B 선거구 내의 구역들이 모두 연결되어있는지 확인
				if(isConnected(B)) {					
					count(A,B); // A,B의 인구수 구하고 차이를 min에 update
				}
			}
			return;
		}
		
		for(int i=start; i<=N; i++) {
			A.add(i);
			combi(idx+1, i+1, cn, A);
			A.remove(idx);
		}
	}

	
	public static boolean isConnected(ArrayList<Integer> area) {
		// 선거구 내의 구역들이 모두 연결되어있나 확인
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		int cnt=1;
		q.add(area.get(0));
		visited[area.get(0)]=true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int i=0; i<city[now].size(); i++) {
				if(area.contains(city[now].get(i)) && !visited[city[now].get(i)]) {
					q.add(city[now].get(i));
					visited[city[now].get(i)]=true;
					cnt++;
				}
			}
		}

		if(cnt!=area.size()) return false;
		else return true;
	}
	
	public static void count(ArrayList<Integer> A, ArrayList<Integer> B) {
		int APop = 0;
		int BPop = 0;
		
		for(int a=0; a<A.size(); a++) {
			APop+=population[A.get(a)];
		}
		for(int b=0; b<B.size(); b++) {
			BPop+=population[B.get(b)];
		}
		
		int AB = Math.abs(APop-BPop);
		min = Math.min(min, AB);
	}
}
