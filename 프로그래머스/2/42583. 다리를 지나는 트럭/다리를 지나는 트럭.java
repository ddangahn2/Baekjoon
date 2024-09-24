import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> leftQ = new LinkedList<>();
        Queue<int[]> moveQ = new LinkedList<>();
        int totalW = 0;
        
        for(int i=0; i<truck_weights.length; i++) {
            leftQ.add(truck_weights[i]);
        }
        
        int answer = 0;
        while (!(leftQ.isEmpty() && moveQ.isEmpty())) {
            answer += 1;
            for(int [] q: moveQ) {
                q[1] -= 1;
            }
            if(!moveQ.isEmpty()) {
                if (moveQ.peek()[1] == 0) {
                    totalW -= moveQ.remove()[0];
                }
            }
            if(!leftQ.isEmpty()) {
                if (totalW + leftQ.peek() <= weight) {
                    totalW += leftQ.peek();
                    int[] newMove = {leftQ.remove(), bridge_length};
                    moveQ.add(newMove);
                }
            }
        }
        
        
        
        return answer;
    }
}