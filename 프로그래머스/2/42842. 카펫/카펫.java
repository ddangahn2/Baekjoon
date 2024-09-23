import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        for(int i=1; i<=(int) Math.sqrt(yellow); i++) {
            if (yellow % i == 0) {
                int r1 = yellow / i;
                int r2 = i;
                
                if((r1+2) * (r2+2) == brown + yellow) {
                    answer[0] = r1+2;
                    answer[1] = r2+2;
                }
            }
        }
        
        return answer;
    }
}