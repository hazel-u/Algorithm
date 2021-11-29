package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Boj_2668 {

    static int[] down;
    static boolean[] visited;
    static int last;
    static LinkedList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        down = new int[N+1];
        visited = new boolean[N+1];
        list = new LinkedList<>();

        for(int n=1; n<=N; n++){
            down[n]=Integer.parseInt(br.readLine());
        }

        for(int n=1; n<=N; n++){
            visited[n]=true;
            last = n;
            dfs(n);
            visited[n]=false;
        }
        Collections.sort(list);

        System.out.println(list.size());
        for(int n=0; n<list.size(); n++){
            System.out.println(list.get(n));
        }
    }

    static void dfs(int n){

        if(!visited[down[n]]){
            visited[down[n]]=true;
            dfs(down[n]);
            visited[down[n]]=false;
        }

        if(down[n]==last){
            list.add(down[n]);
        }
    }

}
