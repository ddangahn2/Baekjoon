class Solution {
    public int solution(int[] money) {
        int max = 0;
        int N = money.length;
        // 0, 1, N-1 3개중 하나 선택한다고 생각하자
        // 일자로 만들 수 있다.
 
        int[] moneyTemp = new int[N-3];
        // 1. 0 (N-1, 0, 1 선택불가)
        int answer = 0;
        answer += money[0];
        for(int i=2; i<N-1; i++) moneyTemp[i-2] = money[i];
        answer += getMax(moneyTemp);
        max = Math.max(max, answer);
        
        // 2. 1 (0, 1, 2 선택불가)
        answer = 0;
        answer += money[1];
        for(int i=3; i<N; i++) moneyTemp[i-3] = money[i];
        answer += getMax(moneyTemp);
        max = Math.max(max, answer);
        
        // 3. N-1 (0, N-1, N-2 선택불가)
        answer = 0;
        answer += money[N-1];
        for(int i=1; i<N-2; i++) moneyTemp[i-1] = money[i];
        answer += getMax(moneyTemp);
        max = Math.max(max, answer);
        
        return max;
    }
    public int getMax(int[] moneyTemp){
        if(moneyTemp.length == 0) return 0;
        else if(moneyTemp.length == 1) return moneyTemp[0];
        else if(moneyTemp.length == 2) return Math.max(moneyTemp[0], moneyTemp[1]);
        
        int N = moneyTemp.length;
        int[] dp = new int[N];
        dp[0] = moneyTemp[0];
        dp[1] = Math.max(moneyTemp[0], moneyTemp[1]);
        for(int i=2; i<N; i++){
            dp[i] = Math.max(dp[i-2] + moneyTemp[i], dp[i-1]);
        }
        return dp[N-1];
    }
}