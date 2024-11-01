import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<=N; j++){
                int prof = Integer.parseInt(st.nextToken());

                dp[i][j] = prof + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }

        int max = -1000;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                int minSize = Math.min(i, j);

                while(minSize > 0){
                    max = Math.max(max, dp[i][j] - dp[i-minSize][j] - dp[i][j-minSize] + dp[i-minSize][j-minSize]);
                    minSize--;
                }
            }
        }

        System.out.println(max);
    }
}