package boj.segmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_9077 {

    static class Pos {
        int x;
        int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            boolean[][] arr = new boolean[10001][10001];
            ArrayList<Pos> bombs = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = true;
                bombs.add(new Pos(x, y));
            }

            int maxFind = 0;
            for (Pos bomb : bombs) {
                int find = findBomb(bomb, arr);
                maxFind = Math.max(maxFind, find);
            }

            bw.write(maxFind + "\n");
        }
        bw.flush();
    }

    public static int findBomb(Pos bomb, boolean[][] arr) {
        //  지뢰를 4모서리에 두고 10x10 지뢰의 개수를 찾는다.
        int x = bomb.x;
        int y = bomb.y;

        int maxFind = 0;

        int find1 = 0;
        int find2 = 0;
        int find3 = 0;
        int find4 = 0;
        for(int i=0; i<=10; i++) {
            for(int j=0; j<=10; j++) {
                // 1. 지뢰가 왼쪽 아래에 있을 때
                if(inRange(x+i, y+j) && arr[x+i][y+j]) find1++;
                // 2. 지뢰가 오른쪽 아래에 있을 때
                if(inRange(x-i, y+j) && arr[x-i][y+j]) find2++;
                // 3. 지뢰가 왼쪽 위에 있을 때
                if(inRange(x+i, y-j) && arr[x+i][y-j]) find3++;
                // 4. 지뢰가 오른쪽 위에 있을 때
                if(inRange(x-i, y-j) && arr[x-i][y-j]) find4++;
            }
        }
        maxFind = Math.max(maxFind, Math.max(find1, Math.max(find2, Math.max(find3, find4))));

        return maxFind;
    }

    public static boolean inRange (int x, int y) {
        if(0>x || x>10000 || 0>y || y>10000) return false;
        return true;
    }
}
