import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String solution(int[] numbers) {
        List<String> arr = new ArrayList<>();
        
        for(int number: numbers) {
            arr.add(Integer.toString(number));
        }
        Collections.sort(arr, (a, b) -> (b+a).compareTo(a+b));
        
        StringBuilder sb = new StringBuilder("");
        
        for(String str: arr){
            sb.append(str);
        }
        if('0' == sb.charAt(0)) {
            return "0";
        }
        return sb.toString();
    }
}