package boj.backTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj_16198 {

    static int max=0;
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        LinkedList<Integer> list = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        solution(list, 0, 0);

        System.out.println(max);
    }

    public static void solution(LinkedList<Integer> list, int energy, int cnt){
        if(cnt==N-2){
            max = Math.max(max, energy);
            return;
        }

        int size = list.size();
        for(int i=1; i<size-1; i++){
            int newEnergy = energy;
            LinkedList<Integer> newList = new LinkedList<>();
            for(int v : list){
                newList.add(v);
            }

            newEnergy+=newList.get(i-1)*newList.get(i+1);
            newList.remove(i);
            solution(newList, newEnergy, cnt+1);
        }
    }
}
