package boj.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1244 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] sw = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			sw[i]=Integer.parseInt(st.nextToken());
		}
		
		int student = Integer.parseInt(br.readLine()); // 학생 수
		
		for(int i=0; i<student; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); // 남 1, 여 2
			int num = Integer.parseInt(st.nextToken()); // 학생이 받은 수
			
			// 남학생일 때
			if(gender == 1) {
				int plus_num=num;
				while(num<=n) {
					sw[num]=(sw[num]+1)%2;
					
					num+=plus_num;
				}
			}
			// 여학생일 때
			else if(gender==2) {
				int part=1;
				w:while(true) {
					if(num-part>0 && num+part<=n) {
						if(sw[num-part]==sw[num+part]) { // 대칭이면
							part++;
							continue;
						}
						else { // 대칭이 아니면
							part--;
							break w;
						}
					}else {
						part--;
						break w;
					}
				}
				
				for(int k=num-part; k<=num+part; k++) {
					sw[k]=(sw[k]+1)%2;
				}
			}
		}
		int cnt = 1;
		while(cnt<=n) {
			System.out.print(sw[cnt]+" ");
			if(cnt%20==0) {
				System.out.println();
			}
			cnt++;
		}
	}

}
