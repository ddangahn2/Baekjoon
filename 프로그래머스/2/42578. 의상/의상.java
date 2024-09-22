import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> clMap = new HashMap<>();
        for(String[] cloth: clothes) {
            clMap.computeIfAbsent(cloth[1], k -> 0);
            clMap.put(cloth[1], clMap.get(cloth[1]) + 1);
        }
        for(Integer count: clMap.values()) {
            answer *= (count + 1);
        }
        answer -= 1;
        return answer;
    }
}