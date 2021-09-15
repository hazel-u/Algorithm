package basic.trie;

public class Trie {
	
	Node root;
	static final int ALPHABET_SIZE=26; // a-z
	public Trie() {
		this.root = new Node();
		this.root.val=' '; // root는 비었다.
	}
		
	private static class Node{
		// 뒤로 연결되는 문자열 a-z 소문자를 index화 하여 저장하는 배열(26개)
		Node[] child = new Node[ALPHABET_SIZE];
		boolean isTerminal = false; // 현재 노드가 종료 노드인지 
		int childNum=0; // 현재 노드에 연결된 문자열의 개수
		char val; // 현재 노드의 값
	}
	
	public static void main(String[] args) {
		// 문자열이 전부 소문자로 이루어졌다고 가정
		Trie trie = new Trie();
        trie.insert("bar");
        trie.insert("bag");
        trie.insert("bark");
        trie.insert("dog");
        trie.insert("do");
        trie.insert("door");

        System.out.println(trie.find("bag") ? "Yes!, bag exists!" : "No, bag does not exist..");

        System.out.println(trie.find("bag") ? "Yes!, bag exists!" : "No, bag does not exist..");
        System.out.println(trie.find("baga") ? "Yes!, baga exists!" : "No, baga does not exist..");

        System.out.println();
	}

	public int charToInt(char c) {
		return c-'a'; // 소문자를 인덱스를 위한 숫자로 변환
	}
	
	public void insert(String str) {
		int length = str.length();
		Node current = this.root; // root부터 시작해서 내려감
		for(int i=0; i<length; i++) {
			char c = str.charAt(i); // 전체 문자열의 일부 단어 추출
			int num = this.charToInt(c); // 추출한 단어를 숫자로 변환
			
			if(current.child[num]==null) { // 기존에 null이면 연결 문자열로 처음 추가되는 것
				current.child[num] = new Node();
				current.child[num].val=c;
				current.child[num].childNum++;
			}
			
			current = current.child[num]; // 자식 노드로 넘어감
		}
		current.isTerminal=true; // 맨 마지막 노드(종료 노드)임
	}
	
	// 반복문으로 노드를 순환하면서 문자열 존재 여부 판단
	public boolean find(String str) {
		int length = str.length();
		Node current = this.root;
		
		for(int i=0; i<length; i++) {
			char c = str.charAt(i);
			int num = this.charToInt(c);
			
			if(current.child[num]==null) { // 문자열의 일부를 추출했는데 null이라면 -> 없다면
				return false;				
			}
			
			// 자식 노드로 넘어가기
			current = current.child[num];
		}
		// 문자열의 마지막이라면 true
		return current!=null && current.isTerminal;
	}
	
}
