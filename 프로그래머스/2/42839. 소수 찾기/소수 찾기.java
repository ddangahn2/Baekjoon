import java.util.*;

class Solution {
    static Set<Integer> set = new HashSet<>();
    static boolean[] isPrime = new boolean[10000000];
    public int solution(String numbers) {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        dfs("", numbers);
        
        int answer = 0;
        isPrimeFunction();
        
        for(int i: set) {
            if(isPrime[i]) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dfs(String prefix, String numbers) {
        if(!prefix.isEmpty()) {
            set.add(Integer.parseInt(prefix));
        }
        for(int i=0; i<numbers.length(); i++) {
            dfs(prefix + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i+1));
        }
    }
    
    public void isPrimeFunction() {
        for(int i=2; i<= Math.sqrt(10000000); i++) {
            if(isPrime[i]) {
                for(int j = i*i; j<10000000; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}