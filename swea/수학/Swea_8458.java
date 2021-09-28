package swea.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Swea_8458 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Integer> points = new ArrayList<>();
			
			int maxD = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dist = Math.abs(x)+Math.abs(y);
				points.add(dist);
				maxD = Math.max(maxD, dist);
			}
			if(maxD==0) System.out.println("#"+t+" "+0);
			else if(!(odd(points) || even(points))) System.out.println("#"+t+" "+(-1));
			else if(even(points)) {
				int tt=val(maxD);
				for(int i=tt; i<63248; i++) {
					long ss = nth(i);
					if(ss%2==0) {
						tt=i;
						break;
					}
				}
				System.out.println("#"+t+" "+tt);
			}
			else if(odd(points)) {
				int tt=val(maxD);
				for(int i=tt; i<63248; i++) {
					long ss = nth(i);
					if(ss%2==1) {
						tt=i;
						break;
					}
				}
				System.out.println("#"+t+" "+tt);
			}
		}
	}
	
	// point들의 거리가 모두 홀수인지 확인
	public static boolean odd(ArrayList<Integer> points) {
		for(int i=0; i<points.size(); i++) {
			if(points.get(i)%2==0) return false;
		}
		
		return true;
	}

	// point들의 거리가 모두 짝수인지 확인
	public static boolean even(ArrayList<Integer> points) {
		for(int i=0; i<points.size(); i++) {
			if(points.get(i)%2==1) return false;
		}
		
		return true;
	}
	
	public static int val(int t) {
		double x = (-1.0 + Math.sqrt(1.0+8.0*t))/2.0;
		return (int)Math.ceil(x);
	}
	
	static long nth(int n) {
		return 0L + n * (n+1L)/2L;
	}
}
