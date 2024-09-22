import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        Queue<Integer> food = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++) {
            food.add(scoville[i]);
        }
        while (food.size() >= 2) {
            if (food.peek() < K) {
                int food1 = food.remove();
                int food2 = food.remove();
                food.add(food2 * 2 + food1);
            } else {
                break;
            }
            answer++;
        }
        if (food.peek() < K) {
            return -1;
        }
        return answer;
    }
}