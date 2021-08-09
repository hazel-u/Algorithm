package swea.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea_9229 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			st=new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[N];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			
			int maxV = -1;
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					int v = arr[i]+arr[j];
					if(v<=M && v>maxV) {
						maxV = v;
					}
				}
			}
			
			System.out.println("#"+t+" "+maxV);
		}
	}

}
