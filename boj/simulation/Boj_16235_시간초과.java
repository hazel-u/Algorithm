package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj_16235_시간초과 {
	
	// 왼상, 상, 오상, 왼, 오, 왼하, 하, 우하
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0 , 1, -1, 1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // map크기
		int M = Integer.parseInt(st.nextToken()); // 처음 심는 나무의 개수
		int K = Integer.parseInt(st.nextToken()); // k년 후 나무 개수 구하기

		int[][] A = new int[N+1][N+1]; // 초기 거름 정보
		int[][] tempA = new int[N+1][N+1]; // 사용되는 거름 정보
		int[][] dieTree = new int[N+1][N+1];
		
		
		LinkedList<Integer>[][] map = new LinkedList[N+1][N+1]; // 나무 정보
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = new LinkedList<>();
				A[i][j] = Integer.parseInt(st.nextToken());
				tempA[i][j]=5;
			}
		}
		
		// 상도가 심은 나무의 정보
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			map[x][y].add(age);
		}
				
		
		// k년 시뮬레이션
		for(int k=0; k<K; k++) {
			// 봄
			for(int r=1; r<=N; r++) {
				for(int c=1; c<=N; c++) {
					Collections.sort(map[r][c]);
					for(int i=0; i<map[r][c].size(); i++) {
						// 지금 보는 나무의 나이보다 양분의 크기가 적으면
						int age = map[r][c].get(i);
						if(age > tempA[r][c]) {							
							// 양분을 못받은 나무 다 죽이기
							int size = map[r][c].size();
							for(int j=i; j<size; j++) {
								dieTree[r][c]+=(map[r][c].get(i)/2); // 죽은 나무는 양분이 되고
								map[r][c].remove(i); // list에서 나무를 없앤다.
							}
							break;
						}
						// 나무의 나이만큼 양분 먹기
						else {							
							tempA[r][c]-=age;
							map[r][c].set(i, age+1);
						}
					}
				}
			}
			
			// 여름
			for(int r=1; r<=N; r++) {
				for(int c=1; c<=N; c++) {
					tempA[r][c]+=dieTree[r][c];
					dieTree[r][c]=0;
				}
			}
			
			// 가을, 겨울
			for(int r=1; r<=N; r++) {
				for(int c=1; c<=N; c++) {
					for(int i=0; i<map[r][c].size(); i++) {
						// 나이가 5배수인 나무 번식
						int age = map[r][c].get(i);
						if(age>0 && age%5==0) {
							for(int d=0; d<8; d++) {
								int nr = r+dr[d];
								int nc = c+dc[d];
								if(nr<1 || nr>N || nc<1 || nc>N) continue; // 상도 땅을 벗어날 경우
								else map[nr][nc].add(1); // 나이가 1인 나무가 생김
							}
						}
					}
					
					// 겨울, 양분 추가하기
					tempA[r][c]+=A[r][c];
				}
			}
		}
		
//		for(int r=1; r<=N; r++) {
//			for(int c=1; c<=N; c++) {
//				for(int i=0; i<map[r][c].size(); i++) {
//					System.out.print(map[r][c].get(i)+" ");
//				}
//				System.out.println();
//			}
//		}
		// k년이 지난 후 살아있는 나무의 개수
		int treeCnt=0;
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				treeCnt+=map[r][c].size();
			}
		}
		
		System.out.println(treeCnt);
	}
}
