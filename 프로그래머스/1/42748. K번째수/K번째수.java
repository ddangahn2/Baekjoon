import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for(int[] command: commands) {
            int i = command[0]-1;
            int j = command[1]-1;
            int k = command[2]-1;
            
            ArrayList<Integer> arr = new ArrayList<>();
            for(int index=i; index<=j; index++) {
                arr.add(array[index]);
            }
            Collections.sort(arr);
            
            result.add(arr.get(k));
        }
        
        int[] answer = result.stream().mapToInt(i->i).toArray();
        return answer;
    }
}