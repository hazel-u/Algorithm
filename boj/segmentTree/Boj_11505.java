package boj.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_11505 {

	static int N,M,K;
	static long[] arr;
	static long[] segArr;
	static int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		segArr = new long[N*4];
		
		for(int i=1; i<=N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(1,1,N);
		
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==1) { // b번째 수를 c로 바꾸기
				arr[b]=c;
				update(1,1,N,b,c);
			}
			else if(a==2) { // b부터 c까지의 곱 구하여 출력
				bw.write(query(1,1,N,b,c)+"\n");
			}
		}
		bw.flush();
	}
	
	public static long init(int idx, int start, int end) {
		int mid = (start+end)/2;
		
		if(start==end) return segArr[idx]=arr[start];
		else return segArr[idx]=(init(idx*2, start, mid)*init(idx*2+1,mid+1,end))%MOD;
	}

	public static long update(int idx, int start, int end, int changeIdx, int changeVal) {
		
		// 바꿔야할게 범위 바깥에 있는 경우
		if(changeIdx<start || changeIdx>end) return segArr[idx];
		// 리프 노드 업데이트
		if(start==end) return segArr[idx]=changeVal;
		
		int mid = (start+end)/2;
		return segArr[idx]=(update(idx*2,start,mid,changeIdx,changeVal)*update(idx*2+1,mid+1,end,changeIdx,changeVal))%MOD;
	}
	
	public static long query(int idx, int start, int end, int fs, int fe) {
		if(fs>end || fe<start) return 1;
		else if(fs<=start && fe>=end) return segArr[idx];
		else {
			int mid = (start+end)/2;
			return (query(idx*2,start,mid,fs,fe)*query(idx*2+1,mid+1,end,fs,fe))%MOD;
		}
	}
}
