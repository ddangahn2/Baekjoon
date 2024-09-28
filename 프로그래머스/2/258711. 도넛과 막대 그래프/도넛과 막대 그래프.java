import java.util.*;

class Solution {
    static int center = 0;
    static int donut = 0;
    static int stick = 0;
    static int eight = 0;
    public int[] solution(int[][] edges) {
        int[] recvCount = new int[1000000];
        int[] sendCount = new int[1000000];
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        
        for(int[] edge: edges) {
            int node1 = edge[0]-1;
            int node2 = edge[1]-1;
            
            graph.computeIfAbsent(node1, k->new ArrayList<Integer>());
            graph.get(node1).add(node2);
            
            sendCount[node1] += 1;
            recvCount[node2] += 1;
        }
        for(Map.Entry<Integer, ArrayList<Integer>> entry: graph.entrySet()) {
            int entryNode = entry.getKey();
            
            if(recvCount[entryNode] == 0 && sendCount[entryNode] > 1) {
                center = entryNode;
                break;
            }
        }
        
        for(int adjNode: graph.get(center)) {
            if(!graph.containsKey(adjNode)) {
                stick += 1;
                continue;
            }
            
            int nextNode = graph.get(adjNode).get(0);
            if(!graph.containsKey(nextNode)) {
                stick += 1;
                continue;
            }
            
            boolean stickFlag = true;
            while(graph.containsKey(nextNode)) {
                if(graph.get(nextNode).size() > 1) {
                    eight += 1;
                    stickFlag = false;
                    break;
                }
                if(nextNode == adjNode) {
                    donut += 1;
                    stickFlag = false;
                    break;
                }
                nextNode = graph.get(nextNode).get(0);
            }
            if(stickFlag) {
                stick += 1;
            }
        }
        
        int[] answer = {center+1, donut, stick, eight};
        return answer;
    }
}