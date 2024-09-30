import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        for(String pa: participant) {
            map.computeIfAbsent(pa, k->0);
            map.put(pa, map.getOrDefault(pa, 0)+1);
        }
        for(String co: completion) {
            map.put(co, map.get(co)-1);
        }
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            if(entry.getValue() != 0) {
                answer = entry.getKey();
            }
        }
        
        return answer;
    }
}