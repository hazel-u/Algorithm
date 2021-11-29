package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj_19535 {

    static int N;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        int d = 0;
        int g = 0;

        for(int n=1; n<=N; n++){
            if(list[n].size()==2){ // ㄷ
                // 적어도 내 옆 둘 중 하나는 2개랑 연결된 아이여야 해
                int first = list[n].get(0);
                int second = list[n].get(1);
                if(first>n && list[first].size()>=2) d+=list[first].size()-1;
                else if(second>n && list[second].size()>=2)d+=list[second].size()-1;
            }else if(list[n].size()>=3){ // ㅈ
                g++;
            }
        }
        /*
            D-트리 : ‘ㄷ’이 ‘ㅈ’의 3배보다 많은 트리
            G-트리 : ‘ㄷ’이 ‘ㅈ’의 3배보다 적은 트리
            DUDUDUNGA-트리 : ‘ㄷ’이 ‘ㅈ’의 정확히 3배만큼 있는 트리
         */

        if(g*3==d) System.out.println("DUDUDUNGA");
        else if(d>g*3) System.out.println("D");
        else if(g>d*3) System.out.println("G");
    }
}
