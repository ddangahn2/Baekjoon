import java.util.*;

class Solution {
    static Map<Character, Integer> map = new HashMap<>();
    public String solution(String[] survey, int[] choices) {
        StringBuilder sb = new StringBuilder("");
        
        for(int i=0; i<survey.length; i++) {
            char type1 = survey[i].charAt(0);
            char type2 = survey[i].charAt(1);
            
            int score = choices[i] - 4;
            if(score < 0) {
                map.put(type1, map.getOrDefault(type1, 0) - score);
            }
            else {
                map.put(type2, map.getOrDefault(type2, 0) + score);
            }
        }
        typeChoose(sb, 'R', 'T');
        typeChoose(sb, 'C', 'F');
        typeChoose(sb, 'J', 'M');
        typeChoose(sb, 'A', 'N');
         
        String answer = sb.toString();
        return answer;
    }
    public static void typeChoose(StringBuilder sb, char type1, char type2){
        if(!map.containsKey(type1) && !map.containsKey(type2)) {
            sb.append(type1);
        }
        else if(!map.containsKey(type1)) {
            sb.append(type2);
        }
        else if(!map.containsKey(type2)) {
            sb.append(type1);
        }
        else {
            if(map.get(type1) < map.get(type2)) {
                sb.append(type2);
            }
            else {
                sb.append(type1);
            }
        }
    }
}