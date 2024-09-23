import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        ArrayList<Integer> a3 = new ArrayList<>();
        
        int[] a1sub = {1,2,3,4,5};
        int[] a2sub = {2,1,2,3,2,4,2,5};
        int[] a3sub = {3,3,1,1,2,2,4,4,5,5};
        
        int a1count = 0;
        int a2count = 0;
        int a3count = 0;
        
        for(int i=0; i<answers.length; i++) {
            if (answers[i] == a1sub[i%5]) {
                a1count += 1;
            }
            if (answers[i] == a2sub[i%8]) {
                a2count += 1;
            }
            if (answers[i] == a3sub[i%10]) {
                a3count += 1;
            }
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, a1count);
        map.put(2, a2count);
        map.put(3, a3count);
        
        ArrayList<Map.Entry<Integer, Integer>> sortMap = new ArrayList<>(map.entrySet());
        // System.out.println(sortMap);
        sortMap.sort((a, b) -> {
            int value = b.getValue().compareTo(a.getValue());
            if(value == 0) {
                return a.getKey().compareTo(b.getKey());
            } else {
                return value;
            }
        });
        
        ArrayList<Integer> ansewrList = new ArrayList<>();
        int maxVal = sortMap.get(0).getValue();
        for(Map.Entry<Integer, Integer> mapEntry: sortMap) {
            if(mapEntry.getValue() == maxVal) {
                ansewrList.add(mapEntry.getKey());
            } else {
                break;
            }
        }
        
        int[] answer = ansewrList.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}