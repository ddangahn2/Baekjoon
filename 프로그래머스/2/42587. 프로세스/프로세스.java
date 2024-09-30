import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(int i=0; i<priorities.length; i++) {
            int[] queue = {i, priorities[i]};
            q.add(queue);
            pq.add(priorities[i]);
        }
        
        int pri = pq.remove();
        
        while(!q.isEmpty()) {
            int[] queue = q.remove();
            if(queue[1] == pri) {
                answer += 1;
                if(queue[0] == location) {
                    return answer;
                }
                pri = pq.remove();
            }
            else {
                q.add(queue);
            }
        }
        
        return answer;
    }
}