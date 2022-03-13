package boj.binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_3745 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s_n = null;

        // 백준에서는 통과되지만 IDE에서는 에러가 나는 코드임
        // 이유를 모르겠네
        while((s_n = br.readLine())!=null) {
            // 입력 시작
            int n = Integer.parseInt(s_n.trim());
            int[] p = new int[n];
            ArrayList<Integer> lis = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                p[i] = Integer.parseInt(st.nextToken());
            }
            // 입력 끝

            // binary search
            int idx=0;
            for(int temp:p) {
                if(idx==0 || temp>lis.get(idx-1)) {
                    lis.add(temp);
                    idx++;
                }
                else {
                    int start=0;
                    int end=idx-1;

                    while(start<end) {
                        int mid = (start+end)/2;

                        if(lis.get(mid)<temp) start = mid+1;
                        else end=mid;
                    }
                    lis.set(end, temp);
                }
            }
            // 출력
            bw.write(idx+"\n");
        }
        bw.flush();
    }
}
