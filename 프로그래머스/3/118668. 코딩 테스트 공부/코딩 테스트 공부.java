import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp;
        int maxCop = cop;
        
        for(int[] problem: problems){
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        int[][] dp = new int[maxAlp+1][maxCop+1];
        
        for(int i=alp; i<=maxAlp; i++){
            for(int j=cop; j<=maxCop; j++){
                dp[i][j] = i + j - alp - cop;
            }
        }
        
        for(int i=alp; i<=maxAlp; i++){
            for(int j=cop; j<=maxCop; j++){
                for(int[] problem: problems){
                    if(i < problem[0] || j < problem[1]){
                        continue;
                    }
                    else{
                        int solAlp = Math.min(i + problem[2], maxAlp);
                        int solCop = Math.min(j + problem[3], maxCop);
                        
                        dp[solAlp][solCop] = Math.min(dp[solAlp][solCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        
//         for(int i=0; i<=maxAlp; i++){
//             for(int j=0; j<=maxCop; j++){
//                 System.out.print(dp[i][j] + " ");
//             }
//             System.out.println();
//         }
        
        return dp[maxAlp][maxCop];
    }
}