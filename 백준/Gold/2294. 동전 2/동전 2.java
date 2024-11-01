import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> arr = new ArrayList<>();
        for(int i=0; i<n; i++){
            arr.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arr);

        int[] dp = new int[k+1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;

        for(int num : arr) {
            for(int i=1; i<=k; i++){
                if(i % num == 0) dp[i] = i/num;
                else if(dp[i] != -1) {
                    if(i + num <= k) dp[i+num] = Math.min(dp[i] + 1, dp[i+num]);
                }
            }
        }

        if(dp[k] == 100001) System.out.println(-1);
        else System.out.println(dp[k]);
    }
}