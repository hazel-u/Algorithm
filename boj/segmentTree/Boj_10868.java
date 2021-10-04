package boj.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_10868 {

	static int N,M;
	static long[] arr;
	static long[] segArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		segArr = new long[N*4];
		Arrays.fill(segArr, Long.MAX_VALUE);
		
		for(int i=1; i<=N; i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		
		init(1,1,N);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			bw.write(query(1,1,N,a,b)+"\n");
		}
		bw.flush();
	}
	
	public static long init(int idx, int start, int end) {
		int mid = (start+end)/2;
		if(start==end) return segArr[idx] = arr[start];
		else return segArr[idx]=Math.min(init(idx*2, start, mid), init(idx*2+1, mid+1, end));
	}
	
	public static long query(int idx, int start, int end, int fs, int fe) {
		if(fs>end || fe<start) return Long.MAX_VALUE;
		else if(fs<=start && fe>=end) return segArr[idx];
		else {
			int mid = (start+end)/2;
			return Math.min(query(idx*2, start, mid, fs, fe),query(idx*2+1, mid+1, end, fs, fe));
		}
	}

}
