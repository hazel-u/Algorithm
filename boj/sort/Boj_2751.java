package boj.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj_2751 {

    static int[] arr;
    static int[] temp;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        temp = new int[N];

        for(int n=0; n<N; n++){
            arr[n]=Integer.parseInt(br.readLine());
        }

        // sorting
        mergesort(0, N-1);

        // print
        for(int n=0; n<N; n++){
            bw.write(arr[n]+"\n");
        }

        bw.flush();

    }

    public static void mergesort(int start, int end){
        if(start<end){
            int mid = (start+end)/2;
            mergesort(start, mid);
            mergesort(mid+1, end);

            int front = start;
            int back = mid+1;
            int index = front;

            while(front<=mid || back<=end){ // 범위 안에 있다면
                if(back>end || (front<=mid && arr[front]<arr[back])){
                    temp[index++] = arr[front++];
                }else{
                    temp[index++] = arr[back++];
                }
            }

            for(int i=start; i<=end; i++){
                arr[i]=temp[i];
            }
            System.out.println(start+" "+end);
            System.out.println(Arrays.toString(arr));
        }
    }
}
