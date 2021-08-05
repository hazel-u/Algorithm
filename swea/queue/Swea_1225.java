package swea.queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_1225 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			int n=Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Queue<Integer> q = new LinkedList<Integer>();
			for(int i=0; i<8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			w:while(true) {
				for(int i=1; i<=5; i++) {
					int temp=q.poll();
					if(temp-i>0) {
						q.offer(temp-i);
					}
					else {
						q.offer(0);
						break w;
					}
				}
			}
			bw.write("#"+t+" ");
			for(int i=0; i<8; i++) {
				bw.write(q.poll()+" ");
			}
			bw.write("\n");
		}
		bw.flush();
	}

}
