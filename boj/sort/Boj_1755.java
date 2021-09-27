package boj.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_1755 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] eng = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		HashMap<String, Integer> map = new HashMap<>();
		LinkedList<String> list = new LinkedList<>();
		
		for(int num=N; num<=M; num++) {
			int first = num/10;
			int second = num%10;
			String s ="";
			
			if(first==0) s=eng[second];
			else s = eng[first]+" "+eng[second];
			
			map.put(s, num);
			list.add(s);
		}
		
		Collections.sort(list);
		
		for(int i=0; i<list.size(); i++) {
			if(i%10==0) bw.write("\n");
			
			bw.write(map.get(list.get(i))+" ");
		}
		
		bw.flush();
	}
}
