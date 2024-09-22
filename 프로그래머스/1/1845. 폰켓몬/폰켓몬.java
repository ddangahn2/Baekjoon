import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        Set<Integer> poSet = new HashSet<>();
        int numsLength = nums.length;
        
        for (int i=0; i<numsLength; i++) {
            poSet.add(nums[i]);
        }
        
        if (poSet.size() > numsLength/2) {
            answer = numsLength/2;
        } else {
            answer = poSet.size();
        }
        
        return answer;
    }
}