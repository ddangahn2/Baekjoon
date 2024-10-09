import java.util.*;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> nodePass = new HashMap<>();
        
//         leafNode 저장하는 arrayList
//         leafNode에 들어간 target 개수 저장하는 거
//         leafNode가 조건 만족했는지 확인하는거
//         
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph.computeIfAbsent(node1, k->new ArrayList<Integer>());
            graph.get(node1).add(node2);
        }
        for(Map.Entry<Integer, ArrayList<Integer>> entry: graph.entrySet()){
            Collections.sort(entry.getValue());
            nodePass.put(entry.getKey(), 0);
        }
        
//         leafNode에 하나씩 떨구기
//         모든 leafNode 만족하는지 확인
//         초과하면 그대로 끝
        
//         숫자배정
//         
        
        int[] answer = {};
        return answer;
    }
}