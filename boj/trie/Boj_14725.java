package boj.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj_14725 {

	static StringBuilder sb;
	
	static class Trie{
		ArrayList<Trie> list;
		String name;
		
		Trie(String name){
			list = new ArrayList<>();
			this.name=name;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		Trie root = new Trie(""); // root
		Trie node;
		
		sb = new StringBuilder();
		
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int k =Integer.parseInt(st.nextToken());
			node = root;
			
			while(k-- > 0) {
				String name = st.nextToken();
				int index=-1;
				
				for(int i=0; i<node.list.size(); i++) {
					// 이어갈 곳 찾기
					if(node.list.get(i).name.equals(name)) {
						index=i;
						break;
					}
				}
				
				// name과 같은 string이 없다면(이어갈 곳이 없다면)
				if(index==-1) {
					node.list.add(new Trie(name)); // 새로 이어붙이기
					node = node.list.get(node.list.size()-1); // 새로 붙인 노드로 이동
				}
				else { // 찾은 노드로 이동
					node = node.list.get(index);
				}
			}
		}
		
		print(root, -1);
		System.out.println(sb.toString());
	}
	
	public static void print(Trie trie, int depth) {
		// 사전 순서대로 먹이 나오기
		Collections.sort(trie.list, new Comparator<Trie>() {

			@Override
			public int compare(Trie o1, Trie o2) {
				return o1.name.compareTo(o2.name);
			}
		});
		
		if(depth!=-1) { // 현재 노드가 root가 아니면
			for(int i=0; i<depth; i++) {
				sb.append("--"); // 지금 있는 노드의 층만큼 -- 적고
			}
			sb.append(trie.name).append("\n"); // 지금 있는 노드의  name적기
		}
		
		for(Trie tr : trie.list) { // 현재 노드와 연결된 자식노드 찾아가기
			print(tr, depth+1);
		}
	}
}
