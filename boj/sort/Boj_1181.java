package boj.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Boj_1181 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		Set<String> s = new HashSet<String>();
		
		for(int i=0; i<N; i++) {
			s.add(br.readLine());
		}
		
		LinkedList<String> arr = new LinkedList<>(s);
		
		Collections.sort(arr, new Comparator<String>() {
			public int compare(String o1, String o2) {
				int temp = o1.length()-o2.length();
				if(temp==0) {
					return o1.compareTo(o2);
				}
				return temp;
			}
		});
		
		for(int i=0; i<arr.size(); i++) {
			bw.write(arr.get(i)+"\n");
		}
		bw.flush();
	}
}
