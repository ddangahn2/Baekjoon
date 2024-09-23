import java.util.*;

class Solution {
    public int solution(int[] citations) {
        
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i: citations){
            arr.add(i);
        }
        
        Collections.sort(arr, Comparator.reverseOrder());
        
        int answer = 0;
        boolean flag = true;
        for(int i=0; i<arr.size(); i++) {
            if (arr.get(i) <= i) {
                answer = i;
                flag = false;
                break;
            }
        }
        if (flag) {
            answer = arr.size();
        }
        return answer;
    }
}