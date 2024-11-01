import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        boolean[] visitB = new boolean[N+1];
        boolean[] visitD = new boolean[N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            graph.computeIfAbsent(v1, k->new ArrayList<>());
            graph.computeIfAbsent(v2, k->new ArrayList<>());

            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        for(Map.Entry<Integer, ArrayList<Integer>> entry: graph.entrySet()){
            Collections.sort(entry.getValue());
        }

        DFS(graph, V, visitD);
        System.out.println();
        BFS(graph, V, visitB);
    }
    public static void DFS(Map<Integer, ArrayList<Integer>> graph, int node, boolean[] visited){
        if(visited[node]) return;
        else {
            visited[node] = true;
            System.out.print(node + " ");
        }
        if(graph.get(node) != null) {
            for(int adjNode: graph.get(node)) DFS(graph, adjNode, visited);
        }
    }
    public static void BFS(Map<Integer, ArrayList<Integer>> graph, int node, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        visited[node] = true;

        while(!q.isEmpty()){
            int curNode = q.remove();
            System.out.print(curNode + " ");

            if(graph.get(curNode) != null){
                for(int adjNode: graph.get(curNode)){
                    if(!visited[adjNode]) {
                        q.add(adjNode);
                        visited[adjNode] = true;
                    }
                }    
            }
        }
    }
}