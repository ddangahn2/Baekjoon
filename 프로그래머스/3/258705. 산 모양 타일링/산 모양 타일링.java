class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        
        int[][] dp = new int[2][n];
        if(tops[0] == 0){
            dp[0][0] = 1;
            dp[1][0] = 2;
        }
        else{
            dp[0][0] = 1;
            dp[1][0] = 3;
        }
        
        for(int i=1; i<n; i++){
            if(tops[i] == 0){
                dp[0][i] = (dp[0][i-1] + dp[1][i-1]) % 10007;
                dp[1][i] = (dp[0][i-1] + 2 * dp[1][i-1]) % 10007;
            }
            else{
                dp[0][i] = (dp[0][i-1] + dp[1][i-1]) % 10007;
                dp[1][i] = (2 * dp[0][i-1] + 3 * dp[1][i-1]) % 10007;
            }
        }
        answer = (dp[0][n-1] + dp[1][n-1]) % 10007;
        
        return answer;
    }
}