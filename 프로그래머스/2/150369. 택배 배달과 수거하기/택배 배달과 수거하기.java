class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
//         끝에서부터 채우고, 끝에서부터 가져와야함
//         가져오는것과 내려놓는것이 독립적이다. -> 어차피 끝집에 가므로, 그 사이 갈때 내리고 올때 픽업하는 과정이다.
        long answer = 0;
        int dOver = 0;
        int pOver = 0;
        for(int i=deliveries.length-1; i>=0; i--) {
            if (deliveries[i] == 0 && pickups[i] == 0) {
                continue;
            }
            int curDeliver = deliveries[i] - dOver;
            int curPickup = pickups[i] - pOver;
            
            if(curDeliver <= 0 && curPickup <= 0) {
                dOver -= deliveries[i];
                pOver -= pickups[i];
            }
            else {
                int curDeliverCount = 1;
                int curPickupCount = 1;
                while (curDeliver > cap * curDeliverCount) {
                    curDeliverCount += 1;
                }
                while (curPickup > cap * curPickupCount) {
                    curPickupCount += 1;
                }
                int maxCount = Math.max(curDeliverCount, curPickupCount);
                dOver = maxCount*cap - curDeliver;
                pOver = maxCount*cap - curPickup;
                
                // System.out.println((i+1) + ":" + maxCount + ":" + dOver + ":" + pOver);
                answer += (i+1) * maxCount;
            }
        }
        answer *= 2;
        
        return answer;
    }
}