package boj.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj_2458 {
	
	static int N,M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
			
		ArrayList<Integer>[] gm = new ArrayList[N+1]; // 나보다 큰 학생들의 번호 저장
		ArrayList<Integer>[] lm = new ArrayList[N+1]; // 나보다 작은 학생들의 번호 저장
		for(int i=1; i<=N; i++) {
			gm[i]=new ArrayList<>();
			lm[i]=new ArrayList<>();
		}
			
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			// a < b
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			gm[a].add(b); // b는 a보다 크다.
			lm[b].add(a); // a는 b보다 작다.
		}
			
		int[] gres = find(gm);
		int[] lres = find(lm);
			
		// 자신의 위치를 알 수 있는 학생 찾기
		int res=0;
		for(int i=1; i<=N; i++) {
			if(gres[i]+lres[i]==N-1) res++;
		}
			
		System.out.println(res);
	}
	
	public static int[] find(ArrayList<Integer>[] cm) {
		int[] res = new int[N+1]; // 나보다 작거나 큰 학생의 수 저장
		
		for(int student=1; student<=N; student++) {
			Stack<Integer> stack = new Stack<>();
			boolean[] visited = new boolean[N+1];
			int studentCnt=0;
			stack.add(student);
			
			while(!stack.isEmpty()) {
				int curS = stack.pop();
				for(int i=0; i<cm[curS].size(); i++) {
					int nS = cm[curS].get(i);
					if(!visited[nS]) {
						visited[nS]=true;
						stack.add(nS);
						studentCnt++;
					}
				}
			}
			
			// 나보다 작거나 큰 학생 수 세기
			res[student]=studentCnt;
		}
		
		return res;
	}

}
