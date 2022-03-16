package boj.trie;

import java.util.NoSuchElementException;

public class Trie {

    Node root; // root node
    static final int ALPHABET_SIZE = 26;

    public Trie() {
        this.root = new Node();
        this.root.val = ' ';
    }

    private static class Node {
        Node[] child = new Node[ALPHABET_SIZE]; // 뒤로 연결되는 문자열 a~z 소문자를 index화하여 저장하는 배열
        boolean isTerminate = false; // 현재 노드가 문자 완성이 되는 노드인지 여부
        int childNum = 0; // 현재 노드에 연결된 문자열의 개수
        char val; // 현재 노드의 값
    }

    private int charToInt(char c) {
        // 문자를 숫자로 변환해주는 메서드
        return c - 'a'; // 현재 코드에서는 소문자만 다루므로 'a'를 빼주면 된다.
    }

    public void insert(String str) {
        // 전체 문자열을 쪼개서 각 노드에 저장하는 메서드
        int length = str.length();
        Node current = this.root; // root부터 시작하며 나감
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i); // 전체 문자열의 일부 단어 추출
            int num = this.charToInt(c); // 추출한 단어를 숫자로 변환

            if (current.child[num] == null) { // 기존에 null이면 연결 문자열로 처음 추가되는 것
                current.child[num] = new Node();
                current.child[num].val = c;
                current.childNum++;
            }

            current = current.child[num]; // 자식 노드로 넘어감
        }
        current.isTerminate = true;
    }

    public boolean find(String str) {
        // 반복문으로 노드를 순환하여 문자열 존재 여부 판단
        int length = str.length();
        Node current = this.root;

        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            int num = this.charToInt(c);
            if (current.child[num] == null) return false; // 문자열을 찾는 도중 null이면 false
            current = current.child[num];
        }
        return current != null && current.isTerminate; // 문자열의 마지막이라면 true
    }

    private char intToChar(int i) {
        return (char) (i + (int) 'a');
    }

    public void printTrie() {
        // 사용자가 간편히 호출만 하면 되는 메서드
        this.printTrie(this.root, 0, new StringBuilder());
    }

    public void printTrie(Node node, int idx, StringBuilder sb) {
        // 내부에서 재귀적으로 순환하여 노드에 저장된 값들 추출해 프린트
        Node current = node;
        Node[] child = current.child;
        StringBuilder builder = new StringBuilder(sb);

        // root노드에는 저장된 것이 없으므로 그 외의 경우만 append
        if (!current.equals(this.root)) {
            builder.append(intToChar(idx));
        }

        // 완성 노드라면 프린팅
        if (current.isTerminate) {
            System.out.println(builder);
        }

        // 연결된 노드들을 순환하기 위해 반복문 사용
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            // null이라면 거기에는 저장된 것이 없다는 의미이므로 건너뜀
            if (current.child[i] == null) continue;
            printTrie(child[i], i, builder);
        }
    }

    public void delete(String str) {
        delete(this.root, str, 0);
    }

    private void delete(Node current, String str, int idx) {
        // 내부적으로 재귀를 통해 삭제를 진행하는 메서드
        int leng = str.length();

        // 자식이 없는데 string의 length의 끝까지 오지 않았다면 예외 발생
        // 끝까지 갔는데 해당 노드가 terminal가 아니었다면 해당 단어를 저장하지 않은 것이므로 예외 발생
        if ((current.childNum == 0 && idx != leng) || (idx == leng && !current.isTerminate)) {
            throw new NoSuchElementException("Value " + str + " does not exist in Trie!");
        }

        // 문자열의 마지막에 다다른 경우
        if (idx == leng) {
            current.isTerminate = false;

            // 자식이 없는데 문자의 마지막이었다면 해당 문자만 저장된 것이므로 null 처리
            if (current.childNum == 0) {
                current = null;
            }
        } else {
            char c = str.charAt(idx);
            int num = charToInt(c);

            // 삭제 후 돌아오는 부분
            delete(current.child[num], str, idx + 1);

            // child가 Null처리 되었다면 자식 노드의 수가 하나 줄어든 것이므로 -- 처리
            if (current.child[num] == null) current.childNum--;

            // 현재 노드의 자식이 없고, 단어의 마지막도 아니라면 삭제해야한다.
            if (current.childNum == 0 && !current.isTerminate) {
                current = null;
            }
        }

    }

    public static void main(String[] args) throws Exception {

        Trie trie = new Trie();
        trie.insert("bar");
        trie.insert("bark");
        trie.insert("bag");
        trie.insert("dog");
        trie.insert("do");
        trie.insert("door");

        System.out.println(trie.find("bag") ? "Yes! bag exist" : "No, bag does not exist");

        trie.delete("bag");
        trie.delete("door");
        trie.delete("do");

        System.out.println(trie.find("bag") ? "Yes! bag exist" : "No, bag does not exist");
        System.out.println(trie.find("baga") ? "Yes! baga exist" : "No, baga does not exist");

        System.out.println("--------------------------------------------");
        trie.printTrie();
    }
}
