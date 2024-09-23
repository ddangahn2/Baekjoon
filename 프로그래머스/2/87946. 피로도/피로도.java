import java.util.*;

class Solution {
    static Set<String> factorialSet = new HashSet<>();
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        int dungeonCount = dungeons.length;
        
        String s = "";
        for(int i=0; i<dungeonCount; i++) {
            s += Integer.toString(i);
        }
        
        factorial("", s, dungeonCount);
        
        for(String fact: factorialSet) {
            int tempK = k;
            int tempAnswer = 0;
            for(int i=0; i<fact.length(); i++) {
                
                int index = fact.charAt(i) - '0';
                
                if (tempK >= dungeons[index][0]) {
                    tempK -= dungeons[index][1];
                    tempAnswer += 1;
                }
            }
            if (answer < tempAnswer) {
                answer = tempAnswer;
            }
        }
        
        return answer;
    }
    
    public void factorial(String made, String left, int fin){
        if(made.length() == fin) {
            factorialSet.add(made);
        }
        for(int i=0; i<left.length(); i++) {
            factorial(made + left.charAt(i), left.substring(0, i) + left.substring(i+1), fin);
        }
    }
}