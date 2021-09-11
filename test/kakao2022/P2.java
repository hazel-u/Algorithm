package test.kakao2022;

public class P2 {

	public static void main(String[] args) {
		int n=437674;
		int k=3;
		System.out.println(solution(n,k));
	}

	public static int solution(int n, int k) {
		int answer=0;
		String changeV = change(n,k); // k진수로 변환
		
		for(int start=0; start<changeV.length(); start++) {
			if(start==0 || changeV.charAt(start-1)=='0') {
				for(int cut=1; cut<=changeV.length()-start; cut++) {
					if(start+cut==changeV.length() || changeV.charAt(start+cut)=='0') {
						if(isPrime(changeV.substring(start, start+cut))) {
							// tempNum 앞뒤에 아무것도 없는 경우
							if(start==0 && start+cut==changeV.length()) answer++;
							// tempNum의 앞에 아무 것도 없고, 뒤에는 0이 있는 경우
							else if(start==0 && start+cut<changeV.length() && changeV.charAt(start+cut)=='0') answer++;
							// tempNum의 앞에 0이 있고, 뒤에 아무것도 없는 경우
							else if(start!=0 && changeV.charAt(start-1)=='0' && start+cut==changeV.length()) answer++;
							// tempNum의 앞 뒤에 0이 있는 경우
							else if(start!=0 && start+cut<changeV.length() && changeV.charAt(start-1)=='0' && changeV.charAt(start+cut)=='0') answer++;
						}
					}
				}
			}
		}
		
		return answer;
	}
	
	public static String change(int n,int k) {
		if(k==10) return Integer.toString(n);
		String ans = "";
		while(n>0) {
			ans = (n%k)+ans;
			n/=k;
		}
		
		return ans;
	}
	
	public static boolean isPrime(String tempS) {
		long n = Long.parseLong(tempS);
		if(n==1) return false;
		// 소수인지 확인
		for(int i=2; i<=Math.sqrt(n); i++) {
            if(n%i==0) return false;
		}


		
		// 수에 0이 포함되었는지 확인
		for(int i=0; i<tempS.length(); i++) {
			if(tempS.charAt(i)=='0') return false;
		}
		//System.out.println("->");
		return true;
	}
}
