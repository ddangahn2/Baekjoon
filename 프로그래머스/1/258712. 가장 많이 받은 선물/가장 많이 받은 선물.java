import java.util.*;

class Solution {
    static Map<String, Integer> giftExchange = new HashMap<>();
    static Map<String, Integer> giftCount = new HashMap<>();
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        for(String friend: friends) {
            giftCount.put(friend, 0);
        }
        for(String gift: gifts) {
            String[] giftFromTo = gift.split(" ");
            String from = giftFromTo[0];
            String to = giftFromTo[1];
            
            giftExchange.computeIfAbsent(gift, k -> 0);
            giftExchange.put(gift, giftExchange.get(gift) + 1);
            
            giftCount.put(from, giftCount.get(from) + 1);
            giftCount.put(to, giftCount.get(to) - 1);
        }
        
        for(int i=0; i<friends.length; i++) {
            int nextMonthGift = 0;
            for(int j=0; j<friends.length; j++) {
                String ItoJ = friends[i] + " " + friends[j];
                String JtoI = friends[j] + " " + friends[i];
                
                boolean isItoJ = giftExchange.containsKey(ItoJ);
                boolean isJtoI = giftExchange.containsKey(JtoI);
                
                boolean sameFlag = false;
                if(isItoJ) {
                    if(!isJtoI) {
                        nextMonthGift += 1;
                    }
                    else {
                        if(giftExchange.get(ItoJ) > giftExchange.get(JtoI)) {
                            nextMonthGift += 1;
                        }
                        else if(giftExchange.get(ItoJ) == giftExchange.get(JtoI)) {
                            sameFlag = true;
                        }
                    }
                }
                if(!isItoJ && !isJtoI) {
                    sameFlag = true;
                }
                if(sameFlag) {
                    if(giftCount.get(friends[i]) > giftCount.get(friends[j])) {
                        nextMonthGift += 1;
                    }
                }
            }
            if(answer < nextMonthGift) {
                answer = nextMonthGift;
            }
        }
        
        return answer;
    }
}