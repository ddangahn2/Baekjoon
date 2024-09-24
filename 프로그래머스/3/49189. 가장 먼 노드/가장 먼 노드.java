import java.util.*;

class Solution {
    static Map<Integer, HashSet<Integer>> graph = new HashMap<>();
    static int[] visitCount;
    
    public int solution(int n, int[][] edges) {
        visitCount = new int[n];

        for(int[] edge: edges) {
            graph.computeIfAbsent(edge[0]-1, k -> new HashSet<>());
            graph.computeIfAbsent(edge[1]-1, k -> new HashSet<>());
            
            graph.get(edge[0]-1).add(edge[1]-1);
            graph.get(edge[1]-1).add(edge[0]-1);
        }
        
        visitCount[0] = 1;
        bfs(0);
        
        Arrays.sort(visitCount);
        
        int maxNum = visitCount[n-1];
        
        int answer = 0;
        for(int i=n-1; i>=0; i--) {
            if(maxNum == visitCount[i]) {
                answer++;
            } else {
                break;
            }
        }
        
        return answer;
    }
    static void bfs(int root) {        
        Queue<int[]> q = new LinkedList<>();
        int[] initQ = {0, 1};
        q.add(initQ);
        
        while(!q.isEmpty()) {
            int[] curQ = q.remove();
            
            for(Integer adjNode: graph.get(curQ[0])){
                // System.out.println()
                if (visitCount[adjNode] == 0) {
                    visitCount[adjNode] = curQ[1]+1;
                    int [] newQ = {adjNode, curQ[1]+1};
                    q.add(newQ);
                }
            }
        }
    }
}