package swea.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Swea_1208 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0; i<10; i++) {
			int N = Integer.parseInt(br.readLine()); 
			
			int[] arr = new int[100];
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<100; j++) {
				arr[j]=Integer.parseInt(st.nextToken());
			}
			
			for(int cnt=0; cnt<N; cnt++) {
				Arrays.sort(arr);
				arr[99]--;
				arr[0]++;
			}
			Arrays.sort(arr);
			System.out.println("#"+(i+1)+" "+(arr[99]-arr[0]));
		}
		
	}

}
