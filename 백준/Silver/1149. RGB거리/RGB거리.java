import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        N 1000
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[3][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            dp[0][i] = Integer.parseInt(st.nextToken());
            dp[1][i] = Integer.parseInt(st.nextToken());
            dp[2][i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<N; i++){
            dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1]) + dp[0][i];
            dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1]) + dp[1][i];
            dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1]) + dp[2][i];
        }

        int min = Math.min(dp[0][N-1], dp[1][N-1]);
        min = Math.min(min, dp[2][N-1]);

        System.out.println(min);
    }
}