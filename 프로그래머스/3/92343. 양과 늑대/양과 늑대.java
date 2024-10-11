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
        // System.out.println(node);
        if(wo >= sh) return;
        max = Math.max(max, sh);
        if(node == -1) return;

        set.remove(node);
        for(int child : graph[node]){
            if(child != -1){
                set.add(child);
            }
        }
        
        for(int adj: set){
            if(info[adj] == 0) search(sh+1, wo, adj, info, new HashSet<>(set));
            else search(sh, wo+1, adj, info, new HashSet<>(set));
        }
    }
}