import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        ArrayList<Integer> al = new ArrayList<>();
        
        al.add(arr[0]);
        int size = 0;
        for(Integer num : arr) {
            if (al.get(size) != num) {
                al.add(num);
                size++;
            }
        }
        
        answer = al.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}