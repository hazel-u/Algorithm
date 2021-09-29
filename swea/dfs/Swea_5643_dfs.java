package swea.dfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Swea_5643_dfs {
	
	static int N,M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			ArrayList<Integer>[] gm = new ArrayList[N+1]; // 나보다 큰 학생들의 번호 저장
			ArrayList<Integer>[] lm = new ArrayList[N+1]; // 나보다 작은 학생들의 번호 저장
			for(int i=1; i<=N; i++) {
				gm[i]=new ArrayList<>();
				lm[i]=new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
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
			
			bw.write("#"+t+" "+res+"\n");
		}
		bw.flush();
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
