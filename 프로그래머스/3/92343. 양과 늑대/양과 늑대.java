import java.util.*;

class Solution {
    static int[][] graph;
    static int max = 1;

    public int solution(int[] info, int[][] edges) {
        graph = new int[info.length][2];
        for(int i=0; i<info.length; i++) Arrays.fill(graph[i], -1);
        
        for(int[] edge: edges){
            if(graph[edge[0]][0] == -1) graph[edge[0]][0] = edge[1];
            else graph[edge[0]][1] = edge[1];
        }
        Set<Integer> set = new HashSet<>();
        search(1, 0, 0, info, set);
        
        return max;
    }
    public static void search(int sh, int wo, int node, int[] info, Set<Integer> set){
        if(wo >= sh) return;
        if(node == -1) return;
        
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        set.remove(node);
        q.add(node);
        int sheep = 0;
        while(!q.isEmpty()){
            int cur = q.remove();
            
            int child1 = graph[cur][0];
            int child2 = graph[cur][1];
            
            if(child1 != -1){
                if(info[child1] == 0) {
                    q.add(child1);
                    sheep++;
                }
                else q2.add(child1);
            }
            if(child2 != -1){
                if(info[child2] == 0) {
                    q.add(child2);
                    sheep++;
                }
                else q2.add(child2);
            }
        }
        set.addAll(q2);
        max = Math.max(max, sh+sheep);
        for(int adj: set){
            search(sh+sheep, wo+1, adj, info, new HashSet<>(set));
        }
    }
}