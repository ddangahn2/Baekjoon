import java.util.*;

class Solution {
    static List<String> arr = new ArrayList<>();
    public int solution(String word) {
        dfs("");
        
        return arr.indexOf(word);
    }
    public void dfs(String s) {
        if(s.length() > 5) {
            return;
        }
        arr.add(s);
        for(int i=0; i<5; i++) {
            dfs(s + "AEIOU".charAt(i));
        }
    }
}