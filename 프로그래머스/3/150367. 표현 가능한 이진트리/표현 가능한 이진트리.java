import java.util.*;

class Solution {
    ArrayList<Integer> arr = new ArrayList<>();
    public int[] solution(long[] numbers) {
//         numbers를 2진수로 변환
//         앞에 0 1개까지 붙일 수 있음. -> 원하는 수만큼 붙일 수 있지
        
        StringBuilder sb;
        for(long number: numbers){
            sb = new StringBuilder("");
            
            while(number > 0) {
                if (number % 2 == 1) {
                    sb.insert(0, "1");
                } else {
                    sb.insert(0, "0");
                }
                number /= 2;
            }
            
            int sbLen = sb.length();
            int pow = 2;
            while((pow-1) < sbLen){
                pow *= 2;
            }
            int prefix = (pow-1) - sbLen;
            sb.insert(0, "0".repeat(prefix));
            
            int st = 0;
            int ed = pow-1;
            
            if(sb.charAt((ed-st) / 2) == '0'){
                arr.add(0);
            } else {
                if(isTree(sb.toString())) {
                    arr.add(1);
                } else {
                    arr.add(0);
                }
            }
        }
        
        int[] answer = arr.stream().mapToInt(i->i).toArray();
        return answer;
    }
    public static boolean isTree(String sb){
        boolean result = true;
        int st = 0;
        int ed = sb.length();
        
        int mid = (ed - st) / 2;
        if(mid == 0) {
            return true;
        }
        if(sb.charAt(mid) == '1') {
            boolean left = isTree(sb.substring(st, mid));
            boolean right = isTree(sb.substring(mid+1, ed));
        
            if(!left || !right) {
                result = false;
            }
        }
        else {
            int move = st;
            while(move < ed) {
                if(sb.charAt(move) == '1'){
                    result = false;
                    break;
                }
                move++;
            }
        }
        return result;
    }
}