import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(int i=0; i<operations.length; i++) {
            String[] operation = operations[i].split(" ");
            
            String op = operation[0];
            int num = Integer.parseInt(operation[1]);
            
            if("I".equals(op)) {
                minHeap.add(num);
                maxHeap.add(num);
            }
            else {
                if (maxHeap.isEmpty()) {
                    continue;
                }
                if(num == 1) {
                    int del = maxHeap.remove();
                    minHeap.remove(del);
                }
                else {
                    int del = minHeap.remove();
                    maxHeap.remove(del);
                }
            }
        }
        int[] answer = new int[2];
        if(!maxHeap.isEmpty()) {
            answer[0] = maxHeap.remove();
            answer[1] = minHeap.remove();
        }
        
        return answer;
    }
}