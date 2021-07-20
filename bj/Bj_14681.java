package bj;

import java.util.Scanner;

public class Bj_14681 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int x = scan.nextInt();
		int y = scan.nextInt();
		
		if(x>0) { // 1,4
			if (y>0) { // 1
				System.out.println(1);
			}
			else { // 4
				System.out.println(4);
			}
		}
		else { // 2,3
			if (y>0) { // 2
				System.out.println(2);
			}
			else { // 3
				System.out.println(3);
			}
		}
	}

}
