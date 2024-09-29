import java.util.*;

class Solution {
    static Set<Integer> set = new HashSet<>();
    static Set<Integer> leftSet = new HashSet<>();
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int sum = cards.length + 1;
        int idx = cards.length / 3;
        
        int round = 1;
        int leftSetPair = 0;
        
//         1. 뽑은 숫자 넣어주고 라운드 계산
        for(int i=0; i<idx; i++){
            if(set.contains(sum - cards[i])) {
                round += 1;
            }
            else {
                set.add(cards[i]);
            }
        }

        for(int i=idx; i<cards.length; i+=2) {
            int card1 = cards[i];
            int card2 = cards[i+1];
            
            round -= 1;
            answer += 1;
            
            if(round < 0) {
                answer -= 1;
                break;
            }
            
            if(set.contains(sum - card1) && coin > 0) {
                coin -= 1;
                round += 1;
            }
            else {
                if(leftSet.contains(sum - card1)) {
                    leftSetPair += 1;
                }
                else {
                    leftSet.add(card1);
                }
            }

            if(set.contains(sum - card2) && coin > 0) {
                coin -= 1;
                round += 1;
            }
            else {
                if(leftSet.contains(sum - card2)) {
                    leftSetPair += 1;
                }
                else {
                    leftSet.add(card2);
                }
            }

            if(round == 0) {
                if(leftSetPair >= 1 && coin >= 2) {
                    leftSetPair -= 1;
                    coin -= 2;
                    round += 1;
                }
            }
        }
        if(round > 0) {
            answer += 1;
        }
        return answer;
    }
}