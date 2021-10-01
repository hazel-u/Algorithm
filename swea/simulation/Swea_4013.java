package swea.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Swea_4013 {
	
	static int[][] info;
	static int[] index;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int K = Integer.parseInt(br.readLine()); // 회전 횟수
			
			info = new int[5][8]; // 4개의 자석 8개의 날
			index = new int[5]; // 각 자석들의 맨 위 인덱스가 현재 몇번인지 저장하는 배열, 초기는 모두 0
			// 비교해야할 인덱스는 2와 6
			
			for(int i=1; i<5; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					info[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int magnet = Integer.parseInt(st.nextToken()); // 움직일 자석
				int dir = Integer.parseInt(st.nextToken())*(-1); // 움직일 방향
				
				move(magnet, dir);
			}
			
			int result=0;
			
			// 자석의 인덱스 확인 (N 0 / S 1)
			for(int i=1; i<=4; i++) {
				if(i==1 && info[i][index[i]]==1) result+=1;
				else if(i==2 && info[i][index[i]]==1) result+=2;
				else if(i==3 && info[i][index[i]]==1) result+=4;
				else if(i==4 && info[i][index[i]]==1) result+=8;
			}
			
			bw.write("#"+t+" "+result+"\n");
		}
		bw.flush();
	}
	
	public static void move(int magnet, int dir) {
		int[] magnetMove = new int[5];
		magnetMove[magnet]=dir;
		int newDir = dir*(-1); // 다음 자석은 반대방향으로 돌아야함
		
		// 앞 자석 체크
		for(int i=magnet; i>1; i--) {
			if(info[i][(index[i]+6)%8] != info[i-1][(index[i-1]+2)%8]) {
				magnetMove[i-1]=newDir;
				newDir*=-1;
			}else break;
		}
		// 뒤 자석 체크
		newDir = dir*(-1);
		for(int i=magnet; i<4; i++) {
			if(info[i][(index[i]+2)%8] != info[i+1][(index[i+1]+6)%8]) {
				magnetMove[i+1]=newDir;
				newDir*=-1;
			}else break;
		}
		
		// magnet의 움직임을 index에 적용
		for(int i=1; i<=4; i++) {
			int tempIndex = index[i]+magnetMove[i];
			if(tempIndex<0) tempIndex+=8;
			index[i] = tempIndex%8;
		}
	}

}
