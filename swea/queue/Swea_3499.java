package swea.queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_3499 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			Queue<String> q1 = new LinkedList<String>();
			Queue<String> q2 = new LinkedList<String>();
			st = new StringTokenizer(br.readLine());
			if(N%2==1) {
				for(int i=0; i<=N/2; i++) {
					q1.offer(st.nextToken());
				}
				while(st.hasMoreTokens()) {
					q2.offer(st.nextToken());
				}
				
			}else {
				for(int i=0; i<N/2; i++) {
					q1.offer(st.nextToken());
				}
				while(st.hasMoreTokens()) {
					q2.offer(st.nextToken());
				}
			}
			
			bw.write("#"+t+" ");
			for(int i=0; i<(N/2)+1; i++) {
				if(!q1.isEmpty()) bw.write(q1.poll()+" ");
				if(!q2.isEmpty()) bw.write(q2.poll()+" ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
}
