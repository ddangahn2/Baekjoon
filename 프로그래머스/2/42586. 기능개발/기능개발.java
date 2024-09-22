import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        ArrayList<Integer> ansList = new ArrayList<>();
        int[] days = new int[progresses.length];
        
        for(int i=0; i<progresses.length; i++) {
            int div = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) {
                div += 1;
            }
            days[i] = div;
        }
        
        int day = days[0];
        int pass = 0;
        for(int i=0; i<days.length; i++) {
            if (days[i] <= day) {
                pass += 1;
            } else {
                ansList.add(pass);
                day = days[i];
                pass = 1;
            }
        }
        ansList.add(pass);
        
        answer = ansList.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}