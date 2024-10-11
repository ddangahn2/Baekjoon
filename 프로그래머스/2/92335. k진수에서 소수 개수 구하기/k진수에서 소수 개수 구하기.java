import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder("");
        List<Long> arr = new ArrayList<>();
        
        while(n > 0){
            if(n % k == 0){
                if(sb.length() > 0){
                    arr.add(Long.parseLong(sb.reverse().toString()));
                    sb = new StringBuilder("");
                }
                n /= k;
                continue;
            }
            sb.append(n % k);
            n /= k;
        }
        if(sb.length() > 0){
            arr.add(Long.parseLong(sb.reverse().toString()));
        }
        
        for(long a: arr) {
            if(isPrime(a)) answer += 1;
        }
        
        return answer;
    }
    public static boolean isPrime(long num){
        if (num < 2) return false;
        
        for(int i=2; i<=Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}