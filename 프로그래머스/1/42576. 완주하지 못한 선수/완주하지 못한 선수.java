import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> paMap = new HashMap<>();
        
        for(String key: participant) {
            paMap.computeIfAbsent(key, k -> 0);
            paMap.put(key, paMap.get(key) + 1);
        }
        for(String key: completion) {
            paMap.put(key, paMap.get(key) - 1);
            if (paMap.get(key) == 0) {
                paMap.remove(key);
            }
        }
        ArrayList<String> paList = new ArrayList<>(paMap.keySet());
        
        answer = paList.get(0);
        
        return answer;
    }
}