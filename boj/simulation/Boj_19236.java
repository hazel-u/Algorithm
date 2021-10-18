package boj.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_19236 {

	static int N = 4;
	static int maxEat = 0;

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };

	static class Fish implements Comparable<Fish> {
		int r;
		int c;
		int dir;
		int num;

		public Fish(int r, int c, int dir, int num) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.num = num;
		}

		@Override
		public int compareTo(Fish o) {
			return this.num - o.num;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		Fish[][] map = new Fish[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int fishN = Integer.parseInt(st.nextToken());
				int fishD = Integer.parseInt(st.nextToken()) - 1;

				map[i][j] = new Fish(i, j, fishD, fishN);
			}
		}

		solve(map, 0, 0, 0, 0);
		System.out.println(maxEat);
	}

	static void solve(Fish[][] map, int sharkR, int sharkC, int sharkD, int eatCnt) {

		// 물고기 먹기
		eatCnt += map[sharkR][sharkC].num;
		sharkD = map[sharkR][sharkC].dir;

		// 복사
		Fish[][] newmap = new Fish[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null) {
					newmap[i][j] = new Fish(i, j, map[i][j].dir, map[i][j].num);
				}
			}
		}
		newmap[sharkR][sharkC] = null;

		// 물고기 이동
		for (int find = 1; find <= 16; find++) {
			// 해당 번호 물고기의 위치찾기
			Fish cur = null;
			for (int fr = 0; fr < 4; fr++) {
				for (int fc = 0; fc < 4; fc++) {
					if (newmap[fr][fc]!=null && newmap[fr][fc].num == find) {
						cur = newmap[fr][fc];
						break;
					}
				}
			}
			if (cur != null) {
				int fd = cur.dir;
				for (int d = 0; d < 8; d++) {
					int nr = cur.r + dr[(fd + d) % 8];
					int nc = cur.c + dc[(fd + d) % 8];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue; // 범위를 벗어나면 pass
					else if (nr == sharkR && nc == sharkC)
						continue; // 상어가 있는 곳이면 pass
					else if (newmap[nr][nc] != null) { // 다른 물고기가 있다면
						// swap
						Fish temp = new Fish(cur.r, cur.c, newmap[nr][nc].dir, newmap[nr][nc].num); // 이미 있던 물고기
						Fish mine = new Fish(nr, nc, (fd + d) % 8, cur.num); // 지금 옮기고 있는 물고기

						newmap[nr][nc] = mine;
						newmap[cur.r][cur.c] = temp;
						break;
					} else if (newmap[nr][nc] == null) { // 빈 곳이라면
						newmap[cur.r][cur.c] = null;
						newmap[nr][nc] = new Fish(nr, nc, (fd + d) % 8, cur.num);

						break;
					}
				}
			}
		}

		// 물고기 정하기
		// 상어의 선택지 최소 0개~최대3개
		boolean sharkMove = false;
		int r = sharkR;
		int c = sharkC;
		for (int m = 1; m <= 3; m++) {
			r += dr[sharkD];
			c += dc[sharkD];

			if (r < N && c < N && r >= 0 && c >= 0 && newmap[r][c] != null) {
				sharkMove = true;
				solve(newmap, r, c, newmap[r][c].dir, eatCnt);
			}
		}

		if (!sharkMove) {
			maxEat = Math.max(maxEat, eatCnt);
			return;
		}
	}

}
