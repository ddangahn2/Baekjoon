import java.util.*;

class Solution {  
    static int answer = 101;
    public int solution(int n, int[][] wires) {
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        
        for(int i=0; i<wires.length; i++){
            graph.computeIfAbsent(wires[i][0], k->new HashSet<>());
            graph.computeIfAbsent(wires[i][1], k->new HashSet<>());
            
            graph.get(wires[i][0]).add(wires[i][1]);
            graph.get(wires[i][1]).add(wires[i][0]);
        }
        
        for(int[] wire: wires) {
            graph.get(wire[0]).remove(wire[1]);
            boolean[] visited = dfs(graph, wire[0], new boolean[n]);
            int trueCount = 0;
            for(int i=0; i<visited.length; i++) {
                if (visited[i] == true) {
                    trueCount += 1;
                }
            }
            int falseCount = n - trueCount;
            if (trueCount > falseCount) {
                if (answer > trueCount - falseCount) {
                    answer = trueCount - falseCount;
                }
            } else {
                if (answer > falseCount - trueCount) {
                    answer = falseCount - trueCount;
                }
            }
            graph.get(wire[0]).add(wire[1]);
        }
        
        return answer;
    }
    public static boolean[] dfs(Map<Integer, HashSet<Integer>> graph, int node, boolean[] visited){
//         node에서 갈 수 있는애들 모두 찾기
        HashSet<Integer> adjNodes = graph.get(node);
        visited[node - 1] = true;
        for(int adjNode: adjNodes) {
             if (!visited[adjNode - 1]) {
                 dfs(graph, adjNode, visited);
             }
        }
        return visited;
    }
}