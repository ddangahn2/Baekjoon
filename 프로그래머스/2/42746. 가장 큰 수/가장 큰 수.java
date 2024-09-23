import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String solution(int[] numbers) {
        
        List<String> arr = new ArrayList<>();
        
        for(int num: numbers) {
            arr.add(Integer.toString(num));
        }
        
        Collections.sort(arr, (a, b) -> (b+a).compareTo(a+b));
        
        String answer = "";
//         for(String str: arr) {
//             if (answer.equals("0") && str.equals("0")) {
//                 break;
//             }
//             answer += str;
//         }
        
//         return answer;
        
        StringBuilder sb = new StringBuilder();
        for(String i : arr) {
            sb.append(i);
        }
        
        answer = sb.toString();
        if(answer.charAt(0) == '0') {
            return "0";
        }else {
            return answer;
        }
    }
}