import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        boolean flag = false;
        HashSet<String> pb = new HashSet<>();
        
        for (String phone: phone_book) {
            pb.add(phone);
        }
        
        for (String phone: phone_book) {
            for(int i=1; i<phone.length(); i++) {
                if (pb.contains(phone.substring(0, i))) {
                    answer = false;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return answer;
    }
}