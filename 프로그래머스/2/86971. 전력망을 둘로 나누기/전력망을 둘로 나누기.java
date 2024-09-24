import java.util.*;

class Solution {  
    static Map<Integer, HashSet<Integer>> graph = new HashMap<>();
    static int N;
    static int result = 101;
    static boolean[] visited;
    public int solution(int n, int[][] wires) {
        visited = new boolean[n];
        N = n;
        for(int[] wire: wires) {
            graph.computeIfAbsent(wire[0], k -> new HashSet<>());
            graph.computeIfAbsent(wire[1], k -> new HashSet<>());
            
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }
        
        dfs(wires[0][0]);
        
        return result;
    }
    static int dfs(int root) {
        visited[root-1] = true;
        
        int count = 1;
        
        for(Integer adjNode: graph.get(root)) {
            if(!visited[adjNode - 1]) {
                count += dfs(adjNode);
            }
        }
        result = Math.min(result, Math.abs(2*count-N));
        
        return count;
    }
}