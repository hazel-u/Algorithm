package swea.전사함수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_6026 {

	static int N,M;
	static int MOD = 1_000_000_007;
	
	static long[] fac; // 테스트케이스를 여러번 돌면서 같은 factorial값이 필요할 때가 있을 수 있기 때문에, 그때그때 구하는 것 보단 미리 구해서 저장해 놓는다.
	
	// fac 배열을 초기화하는 함수
	static void preFac() {
		fac = new long[101]; // N과 M의 최대크기 100
		fac[0] = fac[1] = 1;
		
		for(int i=2; i<101; i++) {
			fac[i] = (fac[i-1]*i)%MOD;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		preFac(); // factorial배열 선언 및 초기화
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			long result = solve();
			bw.write("#"+t+" "+result+"\n");
		}
		bw.flush();
	}
	
	// 함수의 개수 : 시그마(-1)^i * kCi * (k-i)^n
	static long solve() {
		long total = 0;
		
		for(int i=0; i<M; i++) {
			//시그마(-1)^i
			long l1 = (i%2==0)?1:-1; // i가 짝수냐 홀수냐만 판별하면 된다.
			// kCi
			long l2 = nCr(i); // i개가 들어가는 combination을 찾아보자
			// (k-i)^n
			long l3 = pow(M-i,N);
			long result = ((l1*l2)%MOD * l3)%MOD;
			
			// 빼기에서 나머지 연산을 할때 주의할 점 -> 나눠지는 부분에서 음수가 나오면 계산이 복잡해진다. -> 그냥 양수로 바꿔버리자
			// 어차피 뒤에서 나머지 연산을 해주기 때문에 나눠지는 수를 한번 더해주면 된다.
			// result가 음수가 될 수 있으니 그냥 더하지말고 MOD를 한번 더하자
			total = (total+result+MOD) % MOD;
		}
		
		return total;
	}
	
	// n개에서 r개를 구하는 조합의 경우의 수 반환
	static long nCr(int r) {
		// nCr = n! / ((n-r)! * r!)
		// 숫자가 매우 커질 수도 있기 때문에 1,000,000,007(MOD)로 나눈다.
		// nCr = (n! / ((n-r)! * r!)) % MOD
		// 하지만 nCr의 계산 도중 n!과 같은 계산들의 값들이 매우 커질 수 있기 때문에 분배법칙을 적용해주어야 한다.
		// 하지만 분수의 나누기에는 분배법칙을 적용할 수 없다. -> 그래서 페르마의 소정리가 필요하다. (분모를 분자로 올려준다.)
		// 따라서 nCr의 계산은 
		// (n! / ((n-r)! * r!)) % MOD = (n! * ((n-r)! * r!) ^ (MOD-2)) % MOD
		if(r==0) return 1;
		
		// n!
		long l1 = fac[M];
		// ((n-r)! * r!) ^ (mod-2) -> 100 ^ 1000000005 이거 계산이 어마어마해짐 (반씩 나눠서 구하자)
		// 일단 (n-r)! ^ (MOD-2)부터
		long l2 = pow(fac[M-r],MOD-2);
		// r! ^ (MOD-2)
		long l3 = pow(fac[r],MOD-2);
		
		return ((l1*l2)%MOD * l3)%MOD; // 분배법칙 적용
	}
	
	// 반씩 구하자
	static long pow(long a, long b) {
		if(b==1) return a;
		
		long half = pow(a,b/2);
		
		if(b%2==0) return (half*half)%MOD;
		// half*half과정에서 숫자가 엄청나게 커지면 a와의 곱하기가 안될 수도 있다. -> 나머지 연산의 분배법칙을 이용해보자!
		else return ((half*half)%MOD * (a)%MOD)%MOD;
	}

}
