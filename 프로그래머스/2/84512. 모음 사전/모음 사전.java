import java.util.*;

class Solution {
    static int count = 0;
    static int result = 0;
    public int solution(String word) {
        StringBuilder sb = new StringBuilder("");
        dfs(sb, word);
        
        return result;
    }
    public static void dfs(StringBuilder sb, String word) {
        if(sb.length() > 5) {
            return;
        }
        if(word.equals(sb.toString())) {
            result = count;
            return;
        }
        count += 1;
        for(int i=0; i<5; i++) {
            int sbIdx = sb.length();
            sb.append("AEIOU".charAt(i));
            dfs(sb, word);
            sb.deleteCharAt(sbIdx);
        }
    }
}