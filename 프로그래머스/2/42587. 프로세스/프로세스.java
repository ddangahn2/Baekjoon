import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int queueLocation = 0;
        Queue<int[]> queue = new LinkedList<>();
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0; i<priorities.length; i++) {
            pq.add(priorities[i]);
            queue.add(new int[]{i, priorities[i]});
        }
        System.out.println(pq);
        while(!pq.isEmpty()) {
            int pri = pq.remove();
            int index = 0;
            while(true) {
                int[] cur = queue.remove();
                if (cur[1] == pri) {
                    answer += 1;
                    if (cur[0] == location) {
                        return answer;
                    }
                    break;
                } else {
                    queue.add(cur);
                }
            }
        }
        return answer;
    }
}