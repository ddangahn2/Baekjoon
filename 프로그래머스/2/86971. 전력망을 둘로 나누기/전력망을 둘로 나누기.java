import java.util.*;

class Solution {  
    static Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static int[] child;
    static boolean[] visited;
    public int solution(int n, int[][] wires) {
        int answer = 100;
        int count = 100;
        for(int[] wire: wires){
            int node1 = wire[0];
            int node2 = wire[1];
            
            graph.computeIfAbsent(node1, k->new ArrayList<>());
            graph.computeIfAbsent(node2, k->new ArrayList<>());
            
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        visited = new boolean[n+1];
        child = new int[n+1];
        
        dfs(wires[0][0]);
        
        for(int[] wire: wires){
            int node1 = wire[0];
            int node2 = wire[1];
            
            if(child[node1] > child[node2]) {
                count = Math.min(Math.abs(n - 2 * child[node2]), count);
            }
            else {
                count = Math.min(Math.abs(n - 2 * child[node1]), count);
            }
            answer = Math.min(answer, count);
        }
        
        return answer;
    }
    public static int dfs(int root) {
        visited[root] = true;
        int count = 1;
        
        for(int node: graph.get(root)) {
            if(!visited[node]) {
                count += dfs(node);
            }
        }
        child[root] = count;
        return count;
    }
}