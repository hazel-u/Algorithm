package boj.math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj_2755 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashMap<String, Double> score = new HashMap<String, Double>();
        score.put("A+", 4.3);
        score.put("A0", 4.0);
        score.put("A-", 3.7);
        score.put("B+", 3.3);
        score.put("B0", 3.0);
        score.put("B-", 2.7);
        score.put("C+", 2.3);
        score.put("C0", 2.0);
        score.put("C-", 1.7);
        score.put("D+", 1.3);
        score.put("D0", 1.0);
        score.put("D-", 0.7);
        score.put("F", 0.0);

        double total = 0.00;
        int totalClass = 0;

        for(int i=0; i<N; i++){
            // 0: 과목명, 1: 학점, 2: 성적
            String[] input = br.readLine().split(" ");
            totalClass += Integer.parseInt(input[1]);
            total += Integer.parseInt(input[1]) * score.get(input[2]);
        }

        System.out.println(String.format("%.2f", total/totalClass));
    }
}
