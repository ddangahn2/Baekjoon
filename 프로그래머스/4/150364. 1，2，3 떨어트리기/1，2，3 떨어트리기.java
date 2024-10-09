import java.util.*;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> nodePass = new HashMap<>();
        
        Queue<Integer> nodeQ = new LinkedList<>();
        List<Integer> leafNode = new ArrayList<>();
        Map<Integer, Integer> leafNodeCnt = new HashMap<>();
        
        int match = 0;
        
        List<Integer> passWay = new ArrayList<>();
        
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
        
        nodeQ.add(1);
        while(!nodeQ.isEmpty()){
            int node = nodeQ.remove();
            if(graph.containsKey(node)){
                nodeQ.addAll(graph.get(node));
            }
            else{
                leafNode.add(node);
                leafNodeCnt.put(node, 0);
            }
        }
        
        boolean[] leafNodeMatch = new boolean[leafNode.size()];
        for(int i=0; i<leafNode.size(); i++){
            int leaf = leafNode.get(i);
            
            if(target[leaf-1] == 0){
                leafNodeMatch[leafNode.indexOf(leaf)] = true;
                match += 1;
            }
        }
        
        boolean flag = true;
        while(true){
            int node = 1;
            while(!leafNode.contains(node)){
                int pass = nodePass.get(node);
                nodePass.put(node, (pass+1) % graph.get(node).size());
                node = graph.get(node).get(pass);
            }
            
            int getCnt = leafNodeCnt.get(node);
            if((getCnt + 1) <= target[node-1] && target[node-1] <= (getCnt + 1) * 3){
                leafNodeCnt.put(node, leafNodeCnt.get(node) + 1);
                passWay.add(node);
        
                if(!leafNodeMatch[leafNode.indexOf(node)]){
                    leafNodeMatch[leafNode.indexOf(node)] = true;
                    match += 1;
                    
                    if(match == leafNode.size()){
                        break;
                    }
                }
            }
            else if ((getCnt + 1) > target[node-1]){
                flag = false;
                break;
            }
            else{
                leafNodeCnt.put(node, leafNodeCnt.get(node) + 1);
                passWay.add(node);
            }
        }
        
        int[] answer;
        
        if(flag){
            answer = new int[passWay.size()];
            for(int i=0; i<passWay.size(); i++){
                int node = passWay.get(i);
                
                int nodeCnt = leafNodeCnt.get(node);
                leafNodeCnt.put(node, leafNodeCnt.get(node) - 1);
                int remain = target[node-1];
                int num = 0;
                
                if(nodeCnt * 3 == remain){
                    num = 3;
                }
                else if(nodeCnt * 3 - 1 == remain){
                    num = 2;
                }
                else{
                    num = 1;
                }

                target[node-1] -= num;
                answer[i] = num;
            }
        }
        else{
            answer = new int[1];
            answer[0] = -1;
        }

        return answer;
    }
}