package swea.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Swea_1233 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T=10;
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int flag=1;
			
			for(int n=0; n<N; n++) {
				if(flag==1) {
					String[] s = br.readLine().split(" ");
					if(s.length==2) { // leaf인 경우
						if("-+*/".contains(s[1])) flag=0;
					}
					else if(s.length==4) {
						if(!"-+*/".contains(s[1])) flag=0;
					}
					else flag=0;
				}else {
					br.readLine();
				}
			}
			bw.write("#"+t+" "+flag+"\n");
		}
		bw.flush();
	}
}
