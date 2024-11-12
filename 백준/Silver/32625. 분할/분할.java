import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] board = new int[N];

        StringTokenizer str = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) board[i] = Integer.parseInt(str.nextToken());

        boolean res = false;
        for(int i=N-1; i>=1; i--){
            if(N % i != 0) continue;
            boolean tres = true;

            int sum = 0;

            int tmin = 100001;
            int tmax = 0;

            for(int j=0; j<i; j++){
                if(board[j] > tmax) tmax = board[j];
                if(board[j] < tmin) tmin = board[j];
            }
            sum = tmin + tmax;

            for(int j=1; j< N/i; j++){
                tmin = 100001;
                tmax = 0;
                for(int k=j*i; k<(j+1)*i; k++){
                    if(board[k] > tmax) tmax = board[k];
                    if(board[k] < tmin) tmin = board[k];
                }
                if((tmin + tmax) != sum) {
                    tres = false;
                    break;
                }
            }
            if(tres){
                res = true;
                break;
            }
        }
        if(res) System.out.println(1);
        else System.out.println(0);
    }
}