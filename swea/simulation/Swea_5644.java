package swea.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swea_5644 {
	static int[] dr = {0,-1,0,1,0}; //x, 상, 우, 하, 좌
	static int[] dc = {0,0,1,0,-1};
	
	static class Pos{
		int r,c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static class BC{
		int r,c,d,p;

		public BC(int r, int c, int d, int p) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.p = p;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cnt=0;
			
			int M = Integer.parseInt(st.nextToken()); // 총 이동 시간
			int A = Integer.parseInt(st.nextToken()); // BC의 개수
			
			// 사용자 A와 B의 이동 정보
			Queue<Pos> aq = new LinkedList<Pos>();
			Queue<Pos> bq = new LinkedList<Pos>();
			
			// A의 이동정보
			int ar = 1;
			int ac = 1;
			aq.offer(new Pos(ar,ac));
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				int temp = Integer.parseInt(st.nextToken());
				ar += dr[temp];
				ac += dc[temp];
				aq.offer(new Pos(ar,ac));
			}
			
			// B의 이동정보
			int Br = 10;
			int Bc = 10;
			bq.offer(new Pos(Br,Bc));
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				int temp = Integer.parseInt(st.nextToken());
				Br += dr[temp];
				Bc += dc[temp];
				bq.offer(new Pos(Br,Bc));
			}
			
			BC[] bcInfo = new BC[A];
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				bcInfo[i]=new BC(c,r,d,p);
			}
			
			//------------- 입력 끝 ---------------
			
			for(int i=0; i<=M; i++) { // 매 초마다 A와 B를 움직여
				
				Pos a = aq.poll();
				Pos b = bq.poll();
				ArrayList<Integer> alist = new ArrayList<>();
				ArrayList<Integer> blist = new ArrayList<>();
				
				// a와 b가 움직인 주변에 BC가 있나 확인
				for(int bc=0; bc<A; bc++) {
					int bc_r = bcInfo[bc].r;
					int bc_c = bcInfo[bc].c;
					int bc_d = bcInfo[bc].d;
					
					// a확인
					if(Math.abs(a.r-bc_r)+Math.abs(a.c-bc_c)<=bc_d) { // 해당 BC범위 안에 들어오면
						alist.add(bc);
					}
					
					// b확인
					if(Math.abs(b.r-bc_r)+Math.abs(b.c-bc_c)<=bc_d) { // 해당 BC범위 안에 들어오면
						blist.add(bc);
					}
				}
				Collections.sort(alist, new Comparator<Integer>() {

					public int compare(Integer o1, Integer o2) {
						return bcInfo[o2].p-bcInfo[o1].p;
					}
				});
				Collections.sort(blist, new Comparator<Integer>() {

					public int compare(Integer o1, Integer o2) {
						return bcInfo[o2].p-bcInfo[o1].p;
					}
				});
				
				int aSize = alist.size();
				int bSize = blist.size();
				
				
				if(aSize==0 && bSize==0) {
					continue;
				}
				else if(aSize==0) {
					cnt+=bcInfo[blist.get(0)].p;
				}
				else if(bSize==0) {
					cnt+=bcInfo[alist.get(0)].p;
				}
				else {
					if(alist.get(0) == blist.get(0)) {
						
						if(aSize==1 && bSize==1) {
							cnt+=bcInfo[alist.get(0)].p;
						}else if(aSize==1) {
							cnt+=bcInfo[alist.get(0)].p;
							cnt+=bcInfo[blist.get(1)].p;
						}else if(bSize==1) {
							cnt+=bcInfo[alist.get(1)].p;
							cnt+=bcInfo[blist.get(0)].p;
						}else { // a,b 둘 다 2이상
							if(bcInfo[alist.get(1)].p>bcInfo[blist.get(1)].p) {
								cnt+=bcInfo[alist.get(1)].p;
								cnt+=bcInfo[blist.get(0)].p;
							}else {
								cnt+=bcInfo[alist.get(0)].p;
								cnt+=bcInfo[blist.get(1)].p;
							}
						}
						
					}else {
						cnt+=bcInfo[alist.get(0)].p;
						cnt+=bcInfo[blist.get(0)].p;
					}
				}
				
			}
			
			System.out.println("#"+t+" "+cnt);
		}
	}
}
