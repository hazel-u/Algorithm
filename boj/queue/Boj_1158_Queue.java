package boj.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1158_Queue {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=1; i<=N; i++) {
			q.offer(i);
		}
		
		sb.append("<");
		int cnt=0;
		while(!q.isEmpty()) {
			int temp = q.poll();
			if(cnt==K-1) {
				sb.append(temp+", ");
				cnt=0;
			}else {
				q.offer(temp);
				cnt++;
			}
		}
		
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb.toString());
	}

}
