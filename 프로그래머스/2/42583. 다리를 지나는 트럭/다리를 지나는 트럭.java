import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> waitTruck = new LinkedList<>();
        Deque<int[]> goingTruck = new ArrayDeque<>();

        for(int i=0; i<truck_weights.length; i++) {
            waitTruck.add(truck_weights[i]);
        }

        int totalWeight = 0;

        while (!(waitTruck.isEmpty() && goingTruck.isEmpty())){
            Iterator<int[]> iterator = goingTruck.iterator();
            answer++;
            while (iterator.hasNext()) {
                int[] element = iterator.next();
                element[0]--;
                if (element[0] == 0) {
                    totalWeight -= element[1];
                    goingTruck.remove();
                }
            }
            if (!waitTruck.isEmpty()) {
                int newTruck = waitTruck.peek();
                if(totalWeight + newTruck <= weight) {
                    newTruck = waitTruck.remove();
                    goingTruck.add(new int[]{bridge_length ,newTruck});
                    totalWeight += newTruck;
                }
            }
        }
        
        return answer;
    }
}