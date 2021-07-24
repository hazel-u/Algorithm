package boj.basic;

public class Boj_4673 {
	
	static int c (int num) {
		int sum = num;
		
		while(num!=0) {
			sum+=num%10;
			num/=10;
		}
		
		return sum;
	}

	public static void main(String[] args) {
		boolean[] check = new boolean[10001]; // false 초기화
		
		for(int i=1; i<10001; i++) {
			int r = c(i);
			if(r<10001) {
				check[r]=true;
			}
		}
		
		for(int i=1; i<10001; i++) {
			if(check[i]==false) {
				System.out.println(i);
			}
		}
	}

}
