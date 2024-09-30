import java.util.*;

class Solution {
    static Set<Integer> arr = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        StringBuilder sb = new StringBuilder("");
        
        permutation(numbers, sb, numbers.length());
        
        for(int num: arr) {
            if(isPrime(num)) {
                answer += 1;
            }
        }
        
        return answer;
    }
    public static void permutation(String numbers, StringBuilder sb, int len) {
        if(sb.length() > 0) {
            arr.add(Integer.parseInt(sb.toString()));
            if(sb.length() == len) {
                return;
            }
        }
        for(int i=0; i<numbers.length(); i++) {
            int sbLen = sb.length();
            sb.append(numbers.charAt(i));
            permutation(numbers.substring(0,i) + numbers.substring(i+1), sb, len);
            sb.deleteCharAt(sbLen);
        }
    }
    public static boolean isPrime(int num){
        if (num < 2) {
            return false;
        }
        int div = 0;
        for(int i=1; i<=Math.sqrt(num); i++) {
            if(num % i == 0) {
                div += 1;
                if(div > 2) {
                    break;
                }
            }
        }
        if (div == 1) {
            return true;
        }
        return false;
    }
}