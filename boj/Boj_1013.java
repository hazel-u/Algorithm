package boj;
import java.io.*;

public class Boj_1013 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        String regex = "(100+1+|01)+";

        for(int t=0; t<T; t++){
            String str = br.readLine();
            if(str.matches(regex)) bw.write("YES"+"\n");
            else bw.write("NO"+"\n");
        }
        bw.flush();
    }
}
