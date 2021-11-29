package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2473 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){
            arr[n]=Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long[] findV = new long[3];
        long minV=Long.MAX_VALUE;

        for(int n=0; n<N; n++){
            int start = n+1;
            int end = N-1;

            while(start<end){
                long sum = arr[n]+arr[start]+arr[end];
                long abssum = Math.abs(sum);

                if(abssum<minV){
                    minV = abssum;
                    findV[0]=arr[n];
                    findV[1]=arr[start];
                    findV[2]=arr[end];
                }

                if(sum>0) end--;
                else if(sum<0)start++;
                else if(sum==0) {
                    for(int i=0; i<3; i++){
                        System.out.print(findV[i]+" ");
                    }

                    return;
                }
            }
        }
        for(int i=0; i<3; i++){
            System.out.print(findV[i]+" ");
        }
    }
}
