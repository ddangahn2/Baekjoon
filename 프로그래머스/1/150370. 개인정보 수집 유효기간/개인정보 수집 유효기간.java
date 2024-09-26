import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> arr = new ArrayList<>();
        Map<String, Integer> termMap = new HashMap<>();
        for(String term: terms) {
            String[] termSplit = term.split(" ");
            termMap.put(termSplit[0], Integer.parseInt(termSplit[1]));
        }
        String[] todaySplit = today.split("\\.");
        int tY = Integer.parseInt(todaySplit[0]);
        int tM = Integer.parseInt(todaySplit[1]);
        int tD = Integer.parseInt(todaySplit[2]);
        
        for(int i=1; i<=privacies.length; i++) {
            String privacy = privacies[i-1];
            String[] privacySplit = privacy.split(" ");
            int getTerm = termMap.get(privacySplit[1]);
            String[] privacyToday = privacySplit[0].split("\\.");
            int PtY = Integer.parseInt(privacyToday[0]);
            int PtM = Integer.parseInt(privacyToday[1]);
            int PtD = Integer.parseInt(privacyToday[2]);
            
            boolean monthBeforeFlag = false;
            if(PtD > 1) {
                PtD -= 1;
            }
            else {
                PtD = 28;
                monthBeforeFlag = true;
            }
            
            PtM += getTerm;
            if(monthBeforeFlag) {
                PtM -= 1;
            }
            if(PtM == 0) {
                PtY -= 1;
                PtM = 12;
            }
            while (PtM > 12) {
                PtY += 1;
                PtM -= 12;
            }
            
            boolean flag = false;
            if (tY > PtY) {
                flag = true;
            }else if(tY == PtY) {
                if(tM > PtM) {
                    flag = true;
                } else if (tM == PtM) {
                    if(tD > PtD) {
                        flag = true;
                    }
                }
            }
            
            if(flag) {
                arr.add(i);
            }
        }
            
        
        int[] answer = arr.stream().mapToInt(i->i).toArray();
        return answer;
    }
}