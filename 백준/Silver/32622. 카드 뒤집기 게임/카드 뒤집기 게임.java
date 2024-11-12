import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = 0;
        char before = '0';
        int cont1 = 0;
        int cont0 = 0;

        for(int i=0; i<N; i++){
            char c = st.nextToken().charAt(0);
            if(c == '1') {
                if(before == '1') cont1++;
                else {
                    max = Math.max(max, cont0 + cont1);
                    cont1 = 1;
                }
                before = '1';
            }
            else {
                if(before == '0') cont0++;
                else {
                    max = Math.max(max, cont0 + cont1);
                    cont0 = 1;
                }
                before = '0';
            }
        }
        max = Math.max(max, cont0 + cont1);

        System.out.println(max);
    }
}