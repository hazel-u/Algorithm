package programmers.kakao2020;

public class P1 {

	public static void main(String[] args) {
		String s = "aabbaccc";
		System.out.println(solution(s));
	}
	
	static int solution(String s) {
		int sLength = s.length();
		int minLength=sLength+1;
		
		if(sLength==1) return 1;
		
		for(int cut=1; cut<=sLength/2; cut++) {
			StringBuilder sb = new StringBuilder();
			String word=s.substring(0,cut);
			int cnt=1;
			for(int index=cut; index<=sLength-cut; index=index+cut) {
				String tempS = s.substring(index, index+cut);
				if(word.equals(tempS)) {
					cnt++;
				}else {
					if(cnt==1) {
						sb.append(word);
					}
					else {
						sb.append(cnt+word);
						cnt=1;
					}
				}
				word=tempS;
			}
			if(cnt>1) {
				sb.append(cnt+word);
			}else if(cnt==1) {
				sb.append(word);
			}
			
			// 정해진 갯수로 잘리지 못하고 마지막에 남은 문자열 처리
			if(sLength%cut!=0) {
				sb.append(s.substring(sLength-(sLength%cut)));
			}
			minLength = Math.min(minLength, sb.length());
		}
		
		return minLength;
	}
}
