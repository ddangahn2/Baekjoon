import java.util.*;

class Solution {
    static int INF = 20000000;
    static Map<Integer, ArrayList<int[]>> graph = new HashMap<>();
    static int[] sint;
    static int[] ch;
    static int N;
    
    static int min = 0;
    static int minInt = 20000000;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        

        N = n;
        // 이 점이 gate인지, summit인지
        ch = new int[n+1];
        for(int gate: gates) ch[gate] = 1;
        for(int summit: summits) ch[summit] = 2;
        
        for(int[] path: paths){
            graph.computeIfAbsent(path[0], k->new ArrayList<>());
            graph.computeIfAbsent(path[1], k->new ArrayList<>());
            
            graph.get(path[0]).add(new int[]{path[1], path[2]});
            graph.get(path[1]).add(new int[]{path[0], path[2]});
        }
        
        // summit visit 저장.
        Arrays.sort(summits);
        
        for(int i=0; i<summits.length; i++){
            dijkstra(summits[i]);
        }
        int[] answer = {min, minInt};
        // for(int i=0; i<gates.length; i++){
        //     System.out.println(gateInt[i][0] + " " + gateInt[i][1]);
        // }

        return answer;
    }
            
    // 모든 gate마다 dijkstra.
    // gate에서 노드를 건너가는중 다른 gate만나면 이전 gate는 visited하고 해당 점부터 다시 dijk 
    // summit만나면 해당 노드와 intensity기록 -> 다음노드
        
    public static void dijkstra(int node){
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        boolean[] visited = new boolean[N+1];

        int intensity = 0;
        
        for(int[] adj: graph.get(node)){
            // 다른 summit 들어오지 못하게
            if(ch[adj[0]] != 2){
                pq.add(adj);
            }
        }
        visited[node] = true;
        
        while(!pq.isEmpty()){
            int[] next = pq.remove();
            
            if(visited[next[0]]) continue;
            
            intensity = Math.max(intensity, next[1]);
            if(ch[next[0]] == 1) {
                if(minInt > intensity){
                    minInt = intensity;
                    min = node;
                }
                break;
            }
            
            for(int[] adj: graph.get(next[0])){
                if(ch[adj[0]] != 2){
                    pq.add(adj);
                }
            }
            visited[next[0]] = true;
        }
    }
}