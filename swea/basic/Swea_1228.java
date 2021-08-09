package swea.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Swea_1228 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T=10;
		LinkedList<Integer> list;
		
		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
			list = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			// 명령어의 갯수
			int I = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<I; i++) {
				st.nextToken(); // I
				int X = Integer.parseInt(st.nextToken()); // 위치
				int Y = Integer.parseInt(st.nextToken()); // y개의 숫자
				
				LinkedList<Integer> temp = new LinkedList<>();
				for(int y=0; y<Y; y++) {
					temp.add(Integer.parseInt(st.nextToken()));
				}
				
				list.addAll(X, temp);
			}
			
			bw.write("#"+t+" ");
			for(int i=0; i<10; i++) {
				bw.write(list.get(i)+" ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
}
