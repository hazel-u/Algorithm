package boj;
import java.io.*;
import java.util.StringTokenizer;

public class Boj_1991 {

    public static class Node{
        int r;
        int l;
        public Node(int r, int l){
            this.r = r;
            this.l = l;
        }
    }

    static Node[] nodes;
    static int N;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        nodes = new Node[N+1];

        for(int i=0; i<N; i++){
            st=new StringTokenizer(br.readLine());

            int root = st.nextToken().charAt(0)-'A'+1;
            int left = st.nextToken().charAt(0)-'A'+1;
            int right = st.nextToken().charAt(0)-'A'+1;

            nodes[root] = new Node(right,left);
        }

        /*
            preorder    전위 순회한 결과 : ABDCEFG // (루트) (왼쪽 자식) (오른쪽 자식)
            inorder     중위 순회한 결과 : DBAECFG // (왼쪽 자식) (루트) (오른쪽 자식)
            postorder   후위 순회한 결과 : DBEGFCA // (왼쪽 자식) (오른쪽 자식) (루트)
         */

        preorder(1);
        bw.write("\n");
        inorder(1);
        bw.write("\n");
        postorder(1);

        bw.flush();

    }

    static void preorder(int root) throws IOException {
        int right = nodes[root].r;
        int left = nodes[root].l;

        bw.write((char)root+'A'-1);
        if(left != -18) preorder(left);
        if(right != -18) preorder(right);
    }

    static void inorder(int root) throws IOException{
        int right=nodes[root].r;
        int left = nodes[root].l;

        if(left != -18) inorder(left);
        bw.write((char)root+'A'-1);
        if(right != -18) inorder(right);
    }

    static void postorder(int root) throws IOException{
        int right = nodes[root].r;
        int left = nodes[root].l;

        if(left != -18) postorder(left);
        if(right != -18) postorder(right);
        bw.write((char)root+'A'-1);
    }
}
