import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> minHeap = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        StringTokenizer st;
        int total = 0;
        
        for(String operation: operations) {
            st = new StringTokenizer(operation);
            
            String opcode = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            if (opcode.equals("I")) {
                map.computeIfAbsent(num, k -> 0);
                map.put(num, map.get(num) + 1);
                total += 1;
                
                maxHeap.add(num);
                minHeap.add(num);
            } 
            else {
                if (total == 0) {
                    continue;
                }
                total -= 1;
                
                if (num == 1) {
                    while (true) {
                        int maxNum = maxHeap.remove();
                        int maxNumCount = map.get(maxNum);
                        if (maxNumCount == 0) {
                            continue;
                        }
                        else {
                            map.put(maxNum, map.get(maxNum) - 1);
                            break;
                        }
                    }
                } 
                else {
                    while (true) {
                        int minNum = minHeap.remove();
                        int minNumCount = map.get(minNum);
                        if (minNumCount == 0) {
                            continue;
                        }
                        else {
                            map.put(minNum, map.get(minNum) - 1);
                            break;
                        }
                    }
                }
            }
        }
        
        int[] answer = new int[2];
        
        if (total == 0) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            while (true) {
                int maxNum = maxHeap.remove();
                int maxNumCount = map.get(maxNum);
                if (maxNumCount == 0) {
                    continue;
                }
                else {
                    answer[0] = maxNum;
                    break;
                }
            }
            while (true) {
                int minNum = minHeap.remove();
                int minNumCount = map.get(minNum);
                if (minNumCount == 0) {
                    continue;
                }
                else {
                    answer[1] = minNum;
                    break;
                }
            }
        }
        
        return answer;
    }
}