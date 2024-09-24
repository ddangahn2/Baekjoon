import java.util.*;

class Solution {
    static Map<Integer, HashSet<Integer>> winGraph = new HashMap<>();
    static Map<Integer, HashSet<Integer>> loseGraph = new HashMap<>();
    static int[] winChild;
    static int[] loseChild;

    public int solution(int n, int[][] results) {
        
        winChild = new int[n];
        loseChild = new int[n];
        
        for(int[] result: results) {
            int winer = result[0]-1;
            int loser = result[1]-1;
            
            winGraph.computeIfAbsent(winer, k->new HashSet<>());
            loseGraph.computeIfAbsent(loser, k->new HashSet<>());
            
            winGraph.get(winer).add(loser);
            loseGraph.get(loser).add(winer);
        }
        
        
        for(int i=0; i<n; i++) {
            bfs(winGraph, winChild, i);
            bfs(loseGraph, loseChild, i);
        }
        
        int answer = 0;
        
        for(int i=0; i<n; i++) {
            if(winChild[i] + loseChild[i] == n-1) {
                answer++;
            }
        }

        return answer;
    }
    public void bfs(Map<Integer, HashSet<Integer>> graph, int[] child, int i) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        
        while (!q.isEmpty()) {
            int p = q.remove();
            
            if (!graph.containsKey(p)) {
                continue;
            }
            
            for(Integer childNode: graph.get(p)){
                if(!set.contains(childNode)) {
                    child[childNode]++;
                    set.add(childNode);
                    q.add(childNode);
                }
            }
        }
    }
}