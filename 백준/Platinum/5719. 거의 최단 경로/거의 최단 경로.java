import java.util.*;
import java.io.*;

public class Main {
    static int INF = 500000;
    static int N, M, S, D;

    // 전역적으로 공유되는 배열
    static boolean[][] removed;
    static int[] cost;
    static ArrayList<Integer>[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0){
                break;
            }
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            // 전역적으로 공유되는 배열 초기화
            removed = new boolean[N][N];
            cost = new int[N];
            parent = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                parent[i] = new ArrayList<>();
            }

            Map<Integer, ArrayList<int[]>> graph = new HashMap<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                graph.computeIfAbsent(U, k -> new ArrayList<>());
                graph.get(U).add(new int[]{V, P});
            }

            // cost 배열 초기화
            Arrays.fill(cost, INF);

            // 다익스트라 알고리즘 실행 (parent 값 저장)
            dijkstra(graph);

            // 경로 제거
            removeGraph();

            // 다시 cost 배열 초기화 후, 제거된 경로로 다익스트라 실행
            Arrays.fill(cost, INF);
            int newDist = dijkstra(graph);
            System.out.println(newDist == INF? -1: newDist);
        }
    }

    public static int dijkstra(Map<Integer, ArrayList<int[]>> graph){
        int dist = INF;

        Queue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{S, 0});
        cost[S] = 0;

        while(!pq.isEmpty()){
            int[] cur = pq.remove();

            if(cur[1] > cost[cur[0]]) continue;

            if(cur[0] == D) dist = Math.min(dist, cur[1]);
            if(!graph.containsKey(cur[0])) continue;

            for(int[] adj: graph.get(cur[0])){
//                cur[0] -> adj[0], 비용은 cur[1] + adj[1]
                if(removed[cur[0]][adj[0]]) continue;
                int newCost = cur[1] + adj[1];
                if(cost[adj[0]] > newCost){
                    cost[adj[0]] = newCost;
                    parent[adj[0]].clear();
                    parent[adj[0]].add(cur[0]);
                    pq.add(new int[]{adj[0], newCost});
                }
                else if(cost[adj[0]] == newCost){
                    parent[adj[0]].add(cur[0]);
                }
            }
        }

        return dist;
    }

    public static void removeGraph(){
        Queue<Integer> q = new LinkedList<>();
        q.add(D);

        while(!q.isEmpty()){
            int cur = q.remove();

            for(int p: parent[cur]){
                if (!removed[p][cur]) {
                    removed[p][cur] = true;
                    q.add(p);
                }
            }
        }
    }
}
