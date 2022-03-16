package boj.trie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj_5052 {

    static int ALPHABET_SIZE=26;
    static Node root;

    static class Node {
        Node[] child = new Node[ALPHABET_SIZE];
        boolean isTerminate = false;
        int childNum = 0;
        int val;
    }

    public static int charToInt(char c) {
        return c-'0';
    }

    public static boolean insertTrie(String str) {
        int length = str.length();

        Node current = root;

        for(int i=0; i<length; i++) {
            char c = str.charAt(i);
            int num = charToInt(c);

            if(current.child[num] == null) {
                if(current.isTerminate) {
                    // 내 번호는 아직 끝나지 않았는데 해당 노드가 isTerminate되어있을 경우 -> 나의 접두어가 나보다 먼저 insert된 상태
                    return false;
                }
                current.child[num] = new Node();
                current.child[num].val = num;
                current.childNum++;
            }

            current = current.child[num];
        }

        // 내 번호 다 입력했는데 current가 null이 아닌 경우 -> 나를 접두어로 사용하는 번호가 이미 입력되어있는 상태
        if(current.childNum!=0) {
            return false;
        }

        current.isTerminate = true;
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            boolean check = true;
            root = new Node();

            for(int n=0; n<N; n++) {
                if(!insertTrie(br.readLine())) {
                    check=false;
                }
            }
            if(check) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
    }
}
