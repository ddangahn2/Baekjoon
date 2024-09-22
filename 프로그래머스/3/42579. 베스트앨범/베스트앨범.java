import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        ArrayList<Integer> answerList = new ArrayList<>();
        
        Map<String, Integer> genMap = new HashMap<>();
        Map<String, ArrayList<ArrayList<Integer>>> playMap = new HashMap<>();

        for(int i=0; i<genres.length; i++) {
            genMap.computeIfAbsent(genres[i], k -> 0);
            genMap.put(genres[i], genMap.get(genres[i]) + plays[i]);

            playMap.computeIfAbsent(genres[i], k -> new ArrayList<>());
            ArrayList<Integer> play = new ArrayList<>();
            play.add(i);
            play.add(plays[i]);
            playMap.get(genres[i]).add(play);
        }

//        1. genMap sort
        ArrayList<Map.Entry<String, Integer>> sortGenList = new ArrayList<>(genMap.entrySet());
        sortGenList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

//        2. playMap sort
        for(Map.Entry<String, ArrayList<ArrayList<Integer>>> map: playMap.entrySet()) {
            ArrayList<ArrayList<Integer>> value = map.getValue();

            value.sort(Comparator
                    .comparing((ArrayList<Integer> arr) -> arr.get(1), Comparator.reverseOrder())
                    .thenComparing((ArrayList<Integer> arr) -> arr.get(0)));
        }
        
//        3. 결과
        for(Map.Entry<String, Integer> map: sortGenList) {
            if (playMap.get(map.getKey()).size() == 1) {
                answerList.add(playMap.get(map.getKey()).get(0).get(0));
            } else {
                answerList.add(playMap.get(map.getKey()).get(0).get(0));
                answerList.add(playMap.get(map.getKey()).get(1).get(0));
            }
        }
        
        answer = answerList.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}