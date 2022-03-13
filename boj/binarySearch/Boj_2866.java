package boj.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_2866 {

    static int R,C;
    static char[][] words;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        words = new char[R][C];
        for(int i=0; i<R; i++) {
            String str = br.readLine();
            for(int j=0; j<C; j++) {
                words[i][j] = str.charAt(j);
            }
        }

        System.out.println(binarySearch(0, R));
    }

    static int binarySearch(int start, int end) {
        int line = R;
        while(start<=end) {
            int middle = (start+end)/2;

            boolean ch = check(middle);
            if(ch)  {
                line = middle;
                start = middle+1;
            }
            else {
                end = middle-1;
            }
        }
        return line;
    }

    static boolean check(int middle) {
        ArrayList<String> list = new ArrayList<>();

        for(int c=0; c<C; c++) {
            StringBuilder sb = new StringBuilder();
            for(int r=middle; r<R; r++) {
                sb.append(words[r][c]);
            }
            if(list.contains(sb.toString())) return false;
            else list.add(sb.toString());
        }

        return true;
    }
}
