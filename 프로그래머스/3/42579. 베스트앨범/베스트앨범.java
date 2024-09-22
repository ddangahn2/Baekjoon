import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        ArrayList<Integer> answerList = new ArrayList<>();

        Map<String, Integer> genMap = new HashMap<>();
        Map<String, ArrayList<ArrayList<Integer>>> songMap = new HashMap<>();
        for(int i=0; i<genres.length; i++) {
            genMap.computeIfAbsent(genres[i], k -> 0);
            genMap.put(genres[i], genMap.get(genres[i]) + plays[i]);

            songMap.computeIfAbsent(genres[i], k -> new ArrayList<>());
            ArrayList<Integer> song = new ArrayList<>();
            song.add(i);
            song.add(plays[i]);
            songMap.get(genres[i]).add(song);
        }

        List<Map.Entry<String, Integer>> genList = new ArrayList<>(genMap.entrySet());
        genList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for(Map.Entry<String, ArrayList<ArrayList<Integer>>> entry: songMap.entrySet()) {
            ArrayList<ArrayList<Integer>> songs = entry.getValue();

            songs.sort(Comparator
                    .comparing((ArrayList<Integer> arr) -> arr.get(1), Comparator.reverseOrder())
                    .thenComparing((ArrayList<Integer> arr) -> arr.get(0)));
        }

        for(Map.Entry<String, Integer> key: genList) {
            if (songMap.get(key.getKey()).size() == 1) {
                answerList.add(songMap.get(key.getKey()).get(0).get(0));
            } else {
                answerList.add(songMap.get(key.getKey()).get(0).get(0));
                answerList.add(songMap.get(key.getKey()).get(1).get(0));
            }
        }

        answer = answerList.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}