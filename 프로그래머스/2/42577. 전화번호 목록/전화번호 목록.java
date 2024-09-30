import java.util.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        boolean result = true;
        
        Arrays.sort(phoneBook);
        for(int i=phoneBook.length-1; i>0; i--) {
            if(phoneBook[i].startsWith(phoneBook[i-1])) {
                return false;
            }
        }
        
        return result;
    }
}