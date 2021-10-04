package boj.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2357 {

	static int N,M;
	static int[] arr;
	static long[] segMax;
	static long[] segMin;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		segMax = new long[N*4];
		segMin = new long[N*4];
		
		Arrays.fill(segMax, -1);
		Arrays.fill(segMin, Long.MAX_VALUE);
		
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		initMin(1,1,N);
		initMax(1,1,N);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			bw.write(findMin(1,1,N,a,b)+" "+findMax(1,1,N,a,b)+"\n");
		}
		bw.flush();
	}

	public static long initMin(int idx, int start, int end) {
		int mid = (start+end)/2;
		if(start==end) return segMin[idx]=arr[start];
		else return segMin[idx]=Math.min(initMin(idx*2,start,mid),initMin(idx*2+1, mid+1, end));
	}
	
	public static long initMax(int idx, int start, int end) {
		int mid = (start+end)/2;
		if(start==end) return segMax[idx]=arr[start];
		else return segMax[idx]=Math.max(initMax(idx*2, start, mid), initMax(idx*2+1,mid+1,end));
	}
	
	public static long findMin(int idx, int start, int end, int fs, int fe) {
		if(start>fe || end<fs) return Long.MAX_VALUE;
		else if(fs<=start && fe>=end) return segMin[idx];
		else {
			int mid = (start+end)/2;
			return Math.min(findMin(idx*2, start, mid, fs, fe), findMin(idx*2+1,mid+1,end,fs,fe));
		}
	}
	
	public static long findMax(int idx, int start, int end, int fs, int fe) {
		if(fe<start || fs>end) return -1;
		else if(fs<=start && fe>=end) return segMax[idx];
		else {
			int mid = (start+end)/2;
			return Math.max(findMax(idx*2, start, mid, fs, fe), findMax(idx*2+1, mid+1, end, fs, fe));
		}
	}
}
