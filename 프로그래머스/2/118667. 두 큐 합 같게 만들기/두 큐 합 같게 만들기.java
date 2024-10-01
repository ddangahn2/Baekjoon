class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 300000;
        int qLen = queue1.length;
        int[] queue = new int[2*qLen];
        
        long totalSum = 0;
        
        for(int i=0; i<qLen; i++) {
            totalSum += queue1[i];
            totalSum += queue2[i];
            
            queue[i] = queue1[i];
            queue[qLen + i] = queue2[i];
        }
        if (totalSum % 2 != 0) {
            return -1;
        }
        long halfSum = totalSum / 2;
        
        int st = 0;
        int ed = 0;
        long tempSum = queue[0];
        
        while(ed != 2*qLen) {
            if(st > ed){
                break;
            }
            if(tempSum < halfSum) {
                ed += 1;
                if(ed == 2*qLen) {
                    break;
                }
                tempSum += queue[ed];
            }
            else if(tempSum > halfSum) {
                tempSum -= queue[st];
                st += 1;
            }
            else {
                if(ed < qLen-1){
                    answer = Math.min(answer, qLen + (ed + 1) + st);
                }
                else if(ed == qLen-1){
                    answer = Math.min(answer, st);
                }
                else if(ed == 2*qLen-1){
                    if(st >= qLen) {
                        answer = Math.min(st - qLen, answer);
                    }
                    else {
                        answer = Math.min(qLen + st, answer);
                    }
                }
                else{
                    answer = Math.min(answer, ed + 1 + st - qLen);
                }
                    
                tempSum -= queue[st];
                st += 1;
            }
        }
        if (answer == 300000) {
            answer = -1;
        }
        
        return answer;
    }
}

