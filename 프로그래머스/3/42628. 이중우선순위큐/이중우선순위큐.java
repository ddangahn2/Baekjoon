import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> minHeap = new PriorityQueue<>();
        
        int total = 0;
        
        for(String operation: operations) {
            String[] st = operation.split(" ");
            
            int num = Integer.parseInt(st[1]);
            
            if (st[0].equals("I")) {                
                maxHeap.add(num);
                minHeap.add(num);
            } 
            else {
                if(maxHeap.isEmpty()) {
                    continue;
                }
                if (num == 1) {
                    int maxNum = maxHeap.remove();
                    minHeap.remove(maxNum);
                } 
                else {
                    int minNum = minHeap.remove();
                    maxHeap.remove(minNum);
                }
            }
        }
        
        int[] answer = new int[2];
        
        if (maxHeap.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = maxHeap.remove();
            answer[1] = minHeap.remove();
        }
        
        return answer;
    }
}